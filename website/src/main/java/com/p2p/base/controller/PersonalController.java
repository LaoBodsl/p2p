package com.p2p.base.controller;

import com.p2p.base.util.RequireLogin;
import com.p2p.base.domain.Logininfo;
import com.p2p.base.service.IAcountService;
import com.p2p.base.service.UserinfoService;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
}
