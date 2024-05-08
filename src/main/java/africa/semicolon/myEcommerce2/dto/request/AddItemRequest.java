package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

@Data
public class AddItemRequest {
        private String username;
        private String productName;
        private int quantityOfProduct;

}
