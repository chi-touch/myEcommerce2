package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.Payment;
import lombok.Data;

@Data
public class UpdateDeliveryDetailsRequest {

    private String username;
    private String receiverName;
    private String receiverPhoneNumber;
    private String cityName;
    private String countryName;
    private String houseNumber;
    private String street;
    private String state;

    //private String Payment;
}
