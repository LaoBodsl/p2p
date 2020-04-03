package com.p2p.base.mapper;

import com.p2p.base.domain.Iplog;
import java.util.List;

public interface IplogMapper {

    int insert(Iplog record);

    Iplog selectByPrimaryKey(Long id);

    List<Iplog> selectAll();

    int updateByPrimaryKey(Iplog record);
}