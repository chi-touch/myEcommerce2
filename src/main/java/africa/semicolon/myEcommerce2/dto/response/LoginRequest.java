package africa.semicolon.myEcommerce2.dto.response;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
