package africa.semicolon.myEcommerce2.dto.request;


import lombok.Data;

@Data
public class UpdateCreditCardInformationRequest {
   // @NotNull(message = "Username cannot be null")
    private String username;
   // @NotNull(message = "Credit card number cannot be null")
  //  @NotBlank(message = "Credit card number cannot be blank")
    private String creditCardNumber;
   // @NotNull(message = "Card holder name cannot be null")
  //  @NotBlank(message = "Card holder name cannot be blank")
    private String cardHolderName;
   // @NotNull(message = "Card expiration month cannot be null")
  //  @NotBlank(message = "Card expiration month cannot be blank")
    private String cardExpirationMonth;
  //  @NotNull(message = "Card expiration year cannot be null")
   // @NotBlank(message = "Card expiration year cannot be blank")
    private String cardExpirationYear;
   // @NotNull(message = "CVV cannot be null")
  //  @NotBlank(message = "CVV cannot be blank")
    private String cvv;
}
