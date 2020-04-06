package com.mgrsite.base;

import com.p2p.base.query.IplogQueryObject;
import com.p2p.base.service.IplogService;
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
    public String ipLog(@ModelAttribute("qo") IplogQueryObject qo, Model model){
        model.addAttribute("pageResult",iplogService.query(qo));
        return "ipLog/list";
    }
}
