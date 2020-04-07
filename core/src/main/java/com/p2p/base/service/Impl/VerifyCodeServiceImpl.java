package com.p2p.base.service.Impl;

import com.p2p.base.service.VerifyCodeService;
import com.p2p.base.util.BidConst;
import com.p2p.base.util.DateUtil;
import com.p2p.base.util.UserContext;
import com.p2p.base.vo.VerifyCodeVO;
import org.springframework.beans.factory.annotation.AnnotatedBeanDefinition;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class VerifyCodeServiceImpl implements VerifyCodeService {
    @Override
    public void sendVerifyCode(String phoneNumber) {
        //判断当前是否能够发送短信
        //从session中获取最后一次发送时间
        VerifyCodeVO vc = UserContext.getCurrentVerifyCode();

        if(vc==null||DateUtil.secondsBetween(new Date(),vc.getLastSendTime())>90){
            //正常发送
            //生产一个验证码
            //发送短信
            //把手机号,验证码,发送时间转配到vc中并保存到session中
            String verifyCode = UUID.randomUUID().toString().substring(0,4);
            vc = new VerifyCodeVO();
            vc.setPhoneNumber(phoneNumber);
            vc.setVerifyCode(verifyCode);
            vc.setLastSendTime(new Date());
            System.out.println("phoneNum:"+phoneNumber+"code:"+verifyCode);
            UserContext.putCurrentVerifyCode(vc);
        }else {
            throw new RuntimeException("发送过于频繁");
        }
    }

    @Override
    public boolean verify(String phoneNumber, String verifyCode) {
        VerifyCodeVO vc = UserContext.getCurrentVerifyCode();
        if(vc!=null
                &&vc.getPhoneNumber().equals(phoneNumber)
                &&vc.getVerifyCode().equals(verifyCode)
                &&DateUtil.secondsBetween(new Date(),vc.getLastSendTime())<= BidConst.VERIFYCODE_VAILDATE_SECOND){
            return true;
        }
        return false;
    }
}
