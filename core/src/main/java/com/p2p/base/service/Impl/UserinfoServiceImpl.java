package com.p2p.base.service.Impl;

import com.p2p.base.domain.Userinfo;
import com.p2p.base.mapper.UserinfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserinfoServiceImpl implements UserinfoService {
    @Autowired
    private UserinfoMapper userinfoMapper;
    @Override
    public void update(Userinfo userinfo) {
        int ret = this.userinfoMapper.updateByPrimaryKey(userinfo);
        if(ret==0){
            throw new RuntimeException("乐观锁失败,Userinfo:"+userinfo.getId());
        }
    }
}
