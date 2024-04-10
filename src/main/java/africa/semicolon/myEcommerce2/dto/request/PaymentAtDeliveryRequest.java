package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class PaymentAtDeliveryRequest {
    private String deliveryId;
    private BigDecimal amount;
}
