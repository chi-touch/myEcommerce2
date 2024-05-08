package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ProductType;
import africa.semicolon.myEcommerce2.data.model.Role;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {

    private int productQuantity;
    private String productName;
    //private BigDecimal price;
    private ProductType productType;
    private String description;
    private Role role;
    private String username;
    private String userId;

}
