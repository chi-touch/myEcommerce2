package africa.semicolon.myEcommerce2.dto.response;

import lombok.Data;

import java.math.BigDecimal;


@Data
public class TransferResponse {
    private String message;
    private BigDecimal amountPaid;
}
