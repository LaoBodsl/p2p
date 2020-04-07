package com.p2p.base.service.Impl;

import com.p2p.base.domain.Userinfo;
import com.p2p.base.mapper.UserinfoMapper;
import com.p2p.base.service.UserinfoService;
import com.p2p.base.service.VerifyCodeService;
import com.p2p.base.util.BitStatesUtils;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;

    @Autowired
    private VerifyCodeService verifyCodeService;

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

}
