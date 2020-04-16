package com.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * 邮箱验证对象
 */
@Getter
@Setter
public class MailVerify extends BaseDomain{
    private long userinfoId;//绑定对象ID
    private String email;//邮箱
    private String uuid;//uuid
    private Date sendDate;//发送时间
}
