package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

@Data
public class Item {
    private Product product;
    private Integer quantityOfProduct;
}
