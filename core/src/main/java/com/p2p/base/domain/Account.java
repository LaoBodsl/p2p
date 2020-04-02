package com.p2p.base.domain;

import com.p2p.base.util.BidConst;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class Account extends BaseDomain{
    private int version;
    private String tradePassword;//交易密码
    private BigDecimal usableAmount= BidConst.ZERO;//
    private BigDecimal freezedAmount = BidConst.ZERO;
    private BigDecimal unReceiveInterest = BidConst.ZERO;
    private BigDecimal unReceivePrincipal = BidConst.ZERO;
    private BigDecimal unReturnAmount = BidConst.ZERO;
    private BigDecimal remainBorrowLimit = BidConst.INIT_BORROW_LIMIT;
    private BigDecimal borrowLimit = BidConst.INIT_BORROW_LIMIT;

    public BigDecimal getTotalAmount(){
        return usableAmount.add(this.freezedAmount).add(this.unReceivePrincipal);
    }


}
