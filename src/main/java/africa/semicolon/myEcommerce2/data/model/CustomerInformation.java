package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

@Data
public class CustomerInformation {
    private String receiverName;
    private String receiverPhoneNumber;
    private Address deliveryAddress;
    private CreditCardInformation creditCardInfo;
}
