package africa.semicolon.myEcommerce2.dto.response;

import africa.semicolon.myEcommerce2.data.model.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponse {
    private String paymentId;
    private TransactionStatus status;
    private String message;

}
