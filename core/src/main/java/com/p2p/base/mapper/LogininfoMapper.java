package com.p2p.base.mapper;

import com.p2p.base.domain.Logininfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface LogininfoMapper {


    int insert(Logininfo record);

    Logininfo selectByPrimaryKey(Integer id);

    List<Logininfo> selectAll();

    int updateByPrimaryKey(Logininfo record);
    int getCountByUsername(String username);

    /**
     * 登录
     * @param username
     * @param password
     * @return
     */
    Logininfo login(@Param("username")String username, @Param("password")String password);
}