package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

@Data
public class UpdateDeliveryDetailsRequest {
    //@NotNull(message = "Username cannot be null")
    private String username;
    //@NotNull(message = "Receiver name cannot be null")
    //@NotBlank(message = "Receiver name cannot be blank")
    private String receiverName;
    //@NotNull(message = "Receiver phone number cannot be null")
    //@NotBlank(message = "Receiver phone number cannot be blank")
    private String receiverPhoneNumber;
    //@NotNull(message = "City name cannot be null")
    //@NotBlank(message = "City name cannot be blank")
    private String cityName;
    //@NotNull(message = "Country name cannot be null")
    //@NotBlank(message = "Country name cannot be blank")
    private String countryName;
    //@NotNull(message = "House number cannot be null")
    //@NotBlank(message = "House number cannot be blank")
    private String houseNumber;
    //@NotNull(message = "Street name cannot be null")
    //@NotBlank(message = "Street name cannot be blank")
    private String street;
    //@NotNull(message = "State name cannot be null")
    //@NotBlank(message = "State name cannot be blank")
    private String state;
}
