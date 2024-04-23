package africa.semicolon.myEcommerce2.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LogOutResponse {
    private String username;
    private boolean isLoggedIn;
}
