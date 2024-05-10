package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddItemRequest {
        private String password;
        private String username;
        private String productName;
        private int quantityOfProduct;
        private String productId;
        private BigDecimal price;
        private String description;
        private ProductType ProductType;


}
