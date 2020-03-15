package com.p2p.base.domain;

import lombok.Getter;
import lombok.Setter;

/**
 * 用户相关信息
 */

@Setter
@Getter
public class Userinfo extends BaseDomain{
    private int version;//版本号
    private long bitState;//用户状态值
    private String realName;//用户实名
    private String idNumber;//用户身份证号
    private String phoneNumber;//用户手机号

    private SystemDictionaryItem incomeGrade;//收入
    private SystemDictionaryItem marriage;//
    private SystemDictionaryItem kidCount;//
    private SystemDictionaryItem educationBackground;//
    private SystemDictionaryItem houseCondition;//

}
