package africa.semicolon.myEcommerce2.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UpdateCreditCardInformationResponse {
    private String username;
    private String paymentInformation;
    private String message;
}
