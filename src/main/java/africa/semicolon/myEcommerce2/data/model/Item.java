package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Item {
    private Role userRole;
    private String productId;
    private String productName;
    private int quantityOfProduct;
    private BigDecimal price;
}
