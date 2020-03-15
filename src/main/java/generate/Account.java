package generate;

import java.io.Serializable;
import java.math.BigDecimal;
import lombok.Data;

/**
 * account
 * @author 
 */
@Data
public class Account implements Serializable {
    private Long id;

    private String tradepassword;

    private BigDecimal usableamount;

    private BigDecimal freezedamount;

    private BigDecimal borrowlimit;

    private Integer version;

    private BigDecimal unreceiveinterest;

    private BigDecimal unreceiveprincipal;

    private BigDecimal unreturnamount;

    private BigDecimal remainborrowlimit;

    private String verifycode;

    private static final long serialVersionUID = 1L;
}