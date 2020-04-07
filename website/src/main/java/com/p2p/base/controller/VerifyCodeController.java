package com.p2p.base.controller;

import com.p2p.base.service.VerifyCodeService;
import com.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VerifyCodeController {

    @Autowired
    private VerifyCodeService verifyCodeService;

    @RequestMapping("sendVerifyCode")
    @ResponseBody
    public JSONResult sendVerifyCode(String phoneNumber){
        JSONResult json = new JSONResult();
        try {
            System.out.println(phoneNumber);
            verifyCodeService.sendVerifyCode(phoneNumber);

        }catch (RuntimeException re){
            json.setMsg(re.getMessage());
            json.setSuccess(false);
        }
        //System.out.println(phoneNumber);
        return json;
    }
}
