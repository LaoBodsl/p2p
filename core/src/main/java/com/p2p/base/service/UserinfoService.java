package com.p2p.base.service;

import com.p2p.base.domain.Userinfo;

public interface UserinfoService {
    void update(Userinfo userinfo);
    void add(Userinfo userinfo);
    Userinfo get(long id);

    void bindPhone(String phoneNumber, String verifyCode);
}
