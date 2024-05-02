package africa.semicolon.myEcommerce2.dto.request;


import lombok.Data;

@Data
public class UpdateCreditCardInformationRequest {
    private String username;

    private String creditCardNumber;

    private String cardHolderName;

    private String cardExpirationMonth;

    private String cardExpirationYear;

    private String cvv;


}
