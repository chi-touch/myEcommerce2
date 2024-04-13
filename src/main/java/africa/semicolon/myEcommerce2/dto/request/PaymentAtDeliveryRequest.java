package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.TransactionStatus;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentAtDeliveryRequest {
    private String deliveryId;
    private BigDecimal amount;

    private String accountNumber;
    private TransactionStatus status;
}
