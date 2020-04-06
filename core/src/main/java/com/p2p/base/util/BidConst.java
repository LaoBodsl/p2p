package com.p2p.base.util;

import java.math.BigDecimal;

/**
 * 系统中的常亮
 */

public class BidConst {
    public static final int STORE_SCALE=4;//存储精度
    public static final int CAL_SCALE=8;//运算精度
    public static final int DISPLAY_SCALE=2;//显示精度
    public static final BigDecimal ZERO=new BigDecimal("0.0000");
    public static final BigDecimal INIT_BORROW_LIMIT=new BigDecimal("5000.0000");//初始额度
    public static final String DEFAULT_ADMIN_NAME="admin";
    public static final String DEFAULT_ADMIN_PASSWORD="1111";
}
