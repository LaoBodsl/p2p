package com.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class Iplog extends BaseDomain{

    public static final int STATE_SUCCESS=1;
    public static final int STATE_FAILED=0;

    private String ip;
    private int state;
    private String userName;
    private Date loginTime;
    private int userType;

    public String getStateDisplay(){
        return state == STATE_SUCCESS?"登录成功":"登录失败";
    }

    public String getUserTypeDisplay(){
        return userType==Logininfo.USER_CLIENT?"前端用户":"后端用户";
    }
}