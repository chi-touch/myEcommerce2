package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.user.Address;
import africa.semicolon.myEcommerce2.data.model.Role;
import jakarta.persistence.OneToOne;
import lombok.Data;
@Data
public class RegisterRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Role role;
    @OneToOne
    private Address address;
    private String username;
    private boolean isLocked;
}
