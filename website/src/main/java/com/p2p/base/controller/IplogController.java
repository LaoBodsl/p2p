package com.p2p.base.controller;

import com.p2p.base.query.IplogQueryObject;
import com.p2p.base.service.IplogService;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IplogController {
    @Autowired
    IplogService iplogService;

    @RequestMapping("ipLog")
    public String iplogList(@ModelAttribute("qo")IplogQueryObject qo, Model model){
        qo.setUsername(UserContext.getCurrent().getUsername());
        model.addAttribute("pageResult",this.iplogService.query(qo));
        return "iplog_list";
    }
}
