package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

@Data
public class CreditCardInformation {
    private String creditCardNumber;
    private String cardHolderName;
    private String cardExpirationMonth;
    private String cardExpirationYear;
    private String cvv;

    @Override
    public String toString() {
        return "CreditCardInformation{" +
                "creditCardNumber='" + creditCardNumber + '\'' +
                ", cardHolderName='" + cardHolderName + '\'' +
                ", cardExpirationMonth='" + cardExpirationMonth + '\'' +
                ", cardExpirationYear='" + cardExpirationYear + '\'' +
                ", cvv='" + cvv + '\'' +
                ", cardType=" + cardType +
                '}';
    }

    private CardType cardType;

}
