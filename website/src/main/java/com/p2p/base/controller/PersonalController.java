package com.p2p.base.controller;

import com.p2p.base.service.VerifyCodeService;
import com.p2p.base.util.JSONResult;
import com.p2p.base.util.RequireLogin;
import com.p2p.base.domain.Logininfo;
import com.p2p.base.service.IAcountService;
import com.p2p.base.service.UserinfoService;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PersonalController {
    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private IAcountService iAcountService;


    @RequireLogin
    @RequestMapping("personal")
    public String personalCenter(Model model){
        Logininfo current = UserContext.getCurrent();
        model.addAttribute("userinfo", userinfoService.get(current.getId()));
        model.addAttribute("account",iAcountService.get(current.getId()));
        return "personal";
    }

    @RequireLogin
    @RequestMapping("bindPhone")
    @ResponseBody
    public JSONResult bindPhone(String phoneNumber,String verifyCode){
        JSONResult json = new JSONResult();
        try {
            this.userinfoService.bindPhone(phoneNumber,verifyCode);
        }catch (RuntimeException re){
            json.setMsg(re.getMessage());
            json.setSuccess(false);
        }
        return json;
    }

    @RequireLogin
    @RequestMapping("sendEmail")
    @ResponseBody
    public JSONResult bindEmail(String email){
        JSONResult json = new JSONResult();
        try{
            this.userinfoService.sendVerifyEmail(email);
        }catch (RuntimeException re){
            json.setSuccess(false);
            json.setMsg(re.getMessage());
        }
        return json;
    }
    @RequestMapping("bindEmail")
    public String bindEmail(String key,Model model){
        try{
            this.userinfoService.bindEmail(key);
            model.addAttribute("success",true);
        }catch (RuntimeException re){
            model.addAttribute("success",false);
            System.out.println(re.getMessage());
            model.addAttribute("msg",re.getMessage());
        }
        return "checkmail_result";
    }
}
