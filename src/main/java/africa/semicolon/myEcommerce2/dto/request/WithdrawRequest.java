package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class WithdrawRequest {

    private BigDecimal amount;
    private String accountNumber;
    private String pin;
}
