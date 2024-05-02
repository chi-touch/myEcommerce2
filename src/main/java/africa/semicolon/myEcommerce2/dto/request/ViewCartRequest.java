package africa.semicolon.myEcommerce2.dto.request;


import lombok.Data;

@Data
public class ViewCartRequest {

    private String username;
    private String productName;
    private String productQuantity;
    private String productId;

}
