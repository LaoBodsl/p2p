package com.p2p.base.service.Impl;

import com.p2p.base.domain.MailVerify;
import com.p2p.base.domain.Userinfo;
import com.p2p.base.mapper.MailVerifyMapper;
import com.p2p.base.mapper.UserinfoMapper;
import com.p2p.base.service.UserinfoService;
import com.p2p.base.service.VerifyCodeService;
import com.p2p.base.util.BidConst;
import com.p2p.base.util.BitStatesUtils;
import com.p2p.base.util.DateUtil;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private VerifyCodeService verifyCodeService;

    @Autowired
    private MailVerifyMapper mailVerifyMapper;

    @Value("${mail.hostUrl}")
    private String hostUrl;

    @Override
    public void update(Userinfo userinfo) {
        int ret = this.userinfoMapper.updateByPrimaryKey(userinfo);
        if(ret==0){
            throw new RuntimeException("乐观锁失败,Userinfo:"+userinfo.getId());
        }
    }

    @Override
    public void add(Userinfo userinfo) {
        this.userinfoMapper.insert(userinfo);
    }

    @Override
    public Userinfo get(long id) {
        return this.userinfoMapper.selectByPrimaryKey(id);
    }

    @Override
    public void bindPhone(String phoneNumber, String verifyCode) {
        //判断用户是否绑定手机
        //验证验证码合法

        //不合法抛出异常
        Userinfo current = this.get(UserContext.getCurrent().getId());
        if(!current.getIsBindPhone()){
            boolean ret = verifyCodeService.verify(phoneNumber,verifyCode);
            if(ret){
                //如果合法给用户绑定
                current.addState(BitStatesUtils.OP_BIND_PHONE);
                current.setPhoneNumber(phoneNumber);
                this.update(current);
            }else {
                throw new RuntimeException("绑定手机失败");
            }
        }



    }

    @Override
    public Userinfo getCurrent() {
        return this.get(UserContext.getCurrent().getId());
    }

    @Override
    public void sendVerifyEmail(String email) {
        //当前用户是否绑定邮箱
        Userinfo current = this.getCurrent();
        if(!current.getIsBindEmail()){
            String uuid = UUID.randomUUID().toString();
            StringBuilder content = new StringBuilder(100)
                    .append("点击<a href='")
                    .append(this.hostUrl)
                    .append("bindEmail.do?key=").append(uuid)
                    .append("'>这里</a>完成邮箱绑定,有效期为")
                    .append(BidConst.VERIFYEMAIL_VAILDATE_DAY).append("天");

            try{
                 System.out.println("发送邮件:" + email + "邮件内容:" + content);
                //构建一个MailVerify对象
                MailVerify mv = new MailVerify();
                mv.setEmail(email);
                mv.setSendDate(new Date());
                mv.setUserinfoId(current.getId());
                mv.setUuid(uuid);
                this.mailVerifyMapper.insert(mv);
            }catch (Exception e){
                e.printStackTrace();
                throw new RuntimeException("邮箱验证失败");
            }
        }

    }

    @Override
    public void bindEmail(String uuid) {
        //通过uuid获取对象
        MailVerify mv = this.mailVerifyMapper.selectByUUID(uuid);
        if(mv!=null){
            //判断是否绑定邮箱
            Userinfo current = this.get(mv.getUserinfoId());
            if(!current.getIsBindEmail()){
                if(mv!=null&& DateUtil.secondsBetween(mv.getSendDate(),new Date())<=BidConst.VERIFYEMAIL_VAILDATE_DAY*24*3600){
                    //修改用户状态码
                    current.addState(BitStatesUtils.OP_BIND_EMAIL);
                    current.setEmail(mv.getEmail());
                    this.update(current);
                    return;
                }
            }
        }
        throw new RuntimeException("绑定邮箱失败");
    }

}
