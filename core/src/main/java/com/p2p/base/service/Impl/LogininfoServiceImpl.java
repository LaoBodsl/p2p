package com.p2p.base.service.Impl;

import com.p2p.base.domain.Account;
import com.p2p.base.domain.Iplog;
import com.p2p.base.domain.Logininfo;
import com.p2p.base.domain.Userinfo;
import com.p2p.base.mapper.IplogMapper;
import com.p2p.base.mapper.LogininfoMapper;
import com.p2p.base.service.IAcountService;
import com.p2p.base.service.LogininnfoService;
import com.p2p.base.service.UserinfoService;
import com.p2p.base.util.MD5;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class LogininfoServiceImpl implements LogininnfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;

    @Autowired
    private UserinfoService userinfoService;

    @Autowired
    private IAcountService iAcountService;

    @Autowired
    private IplogMapper iplogMapper;
    @Override
    public void register(String username, String password) {
        //判断是否存在
        int count = this.logininfoMapper.getCountByUsername(username);
        //如果不存在
        if(count<=0){
            Logininfo li=new Logininfo();
            li.setUsername(username);
            li.setPassword(MD5.encode(password));
            li.setState(Logininfo.STATE_NORMAl);
            li.setUserType(Logininfo.USER_CLIENT);
            this.logininfoMapper.insert(li);

            //初始化用户信息
            Account account = new Account();
            account.setId(li.getId());
            this.iAcountService.add(account);
            Userinfo ui = new Userinfo();
            ui.setId(li.getId());
            this.userinfoService.add(ui);
        }else {
            //如果存在
            throw new RuntimeException("用户名已存在");
        }
    }

    @Override
    public boolean checkUsername(String username) {
        return logininfoMapper.getCountByUsername(username)>0;
    }

    @Override
    public Logininfo login(String username, String password, String ip, int userType) {
        Logininfo current = this.logininfoMapper.login(username,MD5.encode(password),userType);
        Iplog iplog = new Iplog();
        iplog.setIp(ip);
        iplog.setLoginTime(new Date());
        iplog.setUserName(username);
        iplog.setUserType(userType);

        if(current!=null){
            UserContext.putCurrent(current);
            iplog.setState(Iplog.STATE_SUCCESS);
        }else {
            iplog.setState(Iplog.STATE_FAILED);
        }
        iplogMapper.insert(iplog);
        return current;
    }
}
