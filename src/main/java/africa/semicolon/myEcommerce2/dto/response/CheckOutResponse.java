package africa.semicolon.myEcommerce2.dto.response;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CheckOutResponse {
    private String username;
    private String orderId;
}
