package com.p2p.base.service;

import com.p2p.base.domain.Logininfo;

public interface LogininnfoService {
    /**
     * 用户注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    /**
     * 检查用户是否存在
     * @param username
     * @return
     */
    boolean checkUsername(String username);

    Logininfo login(String username,String password);
}
