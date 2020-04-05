package com.mgrsite.listener;

import com.p2p.base.service.LogininnfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent> {
    @Autowired
    LogininnfoService logininnfoService;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        logininnfoService.initAdmin();

        //System.out.println("spring启动了======>");
    }
}
