package africa.semicolon.myEcommerce2.dto.response;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddProductResponse {
    private String message;

    private String productId;
    private String productName;
    private String category;
    private String description;
    private String price;
    private Integer quantity;

}
