package africa.semicolon.myEcommerce2.dto.request;


import africa.semicolon.myEcommerce2.data.model.Address;
import africa.semicolon.myEcommerce2.data.model.Role;

import lombok.Data;
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
    private String username;
    private boolean isLocked;
}
