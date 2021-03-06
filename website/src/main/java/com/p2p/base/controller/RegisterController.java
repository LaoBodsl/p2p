package com.p2p.base.controller;

import com.p2p.base.domain.Logininfo;
import com.p2p.base.service.LogininnfoService;
import com.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegisterController {
    @Autowired
    private LogininnfoService logininnfoService;

    @RequestMapping("register")
    @ResponseBody
    public JSONResult register(String username, String password){
        JSONResult jsonResult = new JSONResult();
        try {
            logininnfoService.register(username,password);
        } catch (Exception e) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg(e.getMessage());
        }
        return jsonResult;
    }

    @RequestMapping("checkUsername")
    @ResponseBody
    public boolean checkUsername(String username){
        return !this.logininnfoService.checkUsername(username);
    }

    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request){
        JSONResult jsonResult = new JSONResult();

        Logininfo current = this.logininnfoService.login(username,password,request.getRemoteAddr(),Logininfo.USER_CLIENT);
        if(current==null) {
            jsonResult.setSuccess(false);
            jsonResult.setMsg("用户名或者密码错误!");
        }

        return jsonResult;
    }
}
