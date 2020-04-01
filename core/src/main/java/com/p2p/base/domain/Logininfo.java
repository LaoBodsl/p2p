package com.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Logininfo extends BaseDomain{

    public static final int STATE_NORMAl=0;//正常
    public static final int STATE_LOCK=1;//锁定

    private String username;
    private String password;
    private int state;
    //private int userType;
}
