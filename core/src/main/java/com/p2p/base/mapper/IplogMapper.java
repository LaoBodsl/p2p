package com.p2p.base.mapper;

import com.p2p.base.domain.Iplog;
import com.p2p.base.query.IplogQueryObject;

import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);

    int queryForCount(IplogQueryObject qo);

    List<Iplog> query(IplogQueryObject qo);
}