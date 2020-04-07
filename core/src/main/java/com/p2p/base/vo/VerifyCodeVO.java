package com.p2p.base.vo;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class VerifyCodeVO {

    private String verifyCode; //验证码
    private String phoneNumber;//手机号
    private Date lastSendTime;//最后发送时间

}
