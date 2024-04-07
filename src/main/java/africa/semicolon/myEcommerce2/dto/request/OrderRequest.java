package africa.semicolon.myEcommerce2.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class OrderRequest {

    private String amount;

    private String state;
    private String country;
    private String street;
    private String houseNumber;

}
