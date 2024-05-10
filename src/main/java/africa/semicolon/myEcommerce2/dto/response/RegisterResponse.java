package africa.semicolon.myEcommerce2.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class RegisterResponse {
    private String userId;
    private String message;
}
