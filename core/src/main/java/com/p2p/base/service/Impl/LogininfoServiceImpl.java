package com.p2p.base.service.Impl;

import com.p2p.base.domain.Logininfo;
import com.p2p.base.mapper.LogininfoMapper;
import com.p2p.base.service.LogininnfoService;
import com.p2p.base.util.MD5;
import com.p2p.base.util.UserContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LogininfoServiceImpl implements LogininnfoService {

    @Autowired
    private LogininfoMapper logininfoMapper;
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
            this.logininfoMapper.insert(li);
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
    public Logininfo login(String username, String password) {
        Logininfo current = this.logininfoMapper.login(username,MD5.encode(password));
        if(current!=null){
            UserContext.putCurrent(current);
        }else {
            throw new RuntimeException("用户名或密码错误");
        }
        return null;
    }
}
