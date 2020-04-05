package com.mgrsite.base;

import com.p2p.base.domain.Logininfo;
import com.p2p.base.service.LogininnfoService;
import com.p2p.base.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {
    @Autowired
    LogininnfoService logininnfoService;
    @RequestMapping("login")
    @ResponseBody
    public JSONResult login(String username, String password, HttpServletRequest request){
        JSONResult json = new JSONResult();
        Logininfo current = this.logininnfoService.login(username,password,request.getRemoteAddr(),Logininfo.USER_MANAGER);
        if(current==null){
            json.setSuccess(false);
            json.setMsg("用户名或者密码错误");
        }
        return json;
    }
}
