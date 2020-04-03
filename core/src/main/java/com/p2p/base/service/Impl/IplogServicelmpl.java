package com.p2p.base.service.Impl;

import com.p2p.base.domain.Iplog;
import com.p2p.base.mapper.IplogMapper;
import com.p2p.base.query.IplogQueryObject;
import com.p2p.base.query.PageResult;
import com.p2p.base.service.IplogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IplogServicelmpl implements IplogService {
    @Autowired
    IplogMapper iplogMapper;
    @Override
    public PageResult query(IplogQueryObject qo) {
        int count = this.iplogMapper.queryForCount(qo);
        if(count>0){
            List<Iplog> list = this.iplogMapper.query(qo);
            return new PageResult(list,count,qo.getCurrentPage(),qo.getPageSize());
        }
        return PageResult.empty(qo.getPageSize());
    }
}
