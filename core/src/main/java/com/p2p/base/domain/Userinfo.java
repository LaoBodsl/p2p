package com.p2p.base.domain;

import com.p2p.base.util.BitStatesUtils;
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
    private String email;//用户邮箱

    private SystemDictionaryItem incomeGrade;//收入
    private SystemDictionaryItem marriage;//
    private SystemDictionaryItem kidCount;//
    private SystemDictionaryItem educationBackground;//
    private SystemDictionaryItem houseCondition;//

    /**
     * 添加状态码
     * @param state
     */
    public void addState(long state){
        this.setBitState(BitStatesUtils.addState(this.bitState,state));
    }

    public void removeState(long state){
        this.setBitState(BitStatesUtils.removeState(this.bitState,state));
    }

    /**
     * 获取是否绑定手机的状态
     * @return
     */
    public boolean getIsBindPhone(){
        return BitStatesUtils.hasState(this.bitState,BitStatesUtils.OP_BIND_PHONE);
    }


    /**
     * 获取绑定邮箱状态
     * @return
     */
    public boolean getIsBindEmail(){
        return BitStatesUtils.hasState(this.bitState,BitStatesUtils.OP_BIND_EMAIL);
    }
}
