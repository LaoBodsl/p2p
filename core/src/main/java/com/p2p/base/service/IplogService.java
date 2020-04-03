package com.p2p.base.service;

import com.p2p.base.query.IplogQueryObject;
import com.p2p.base.query.PageResult;

public interface IplogService {
    PageResult query(IplogQueryObject op);

}
