package africa.semicolon.myEcommerce2.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
public class GetItemResponse {
    private String productId;
    private String productName;
    private String category;
    private String description;
    private BigDecimal price;
    private int quantityOfProduct;
}
