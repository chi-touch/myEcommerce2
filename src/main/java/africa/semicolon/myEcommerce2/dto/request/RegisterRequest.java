package africa.semicolon.myEcommerce2.dto.request;


import africa.semicolon.myEcommerce2.data.model.*;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.util.List;

@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;


    private String state;
    private String country;
    private String street;
    private String houseNumber;

   // private String receiverName;
    private String receiverPhoneNumber;

    private String creditCardNumber;
    private String cardHolderName;
    private String cardExpirationMonth;
    private String cardExpirationYear;



    private String username;
}
