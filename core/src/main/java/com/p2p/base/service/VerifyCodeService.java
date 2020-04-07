package com.p2p.base.service;

import org.springframework.stereotype.Service;


public interface VerifyCodeService {

    /**
     * 给手机发送验证码
     * @param phoneNumber
     */
    void sendVerifyCode(String phoneNumber);

    boolean verify(String phoneNumber, String verifyCode);
}
