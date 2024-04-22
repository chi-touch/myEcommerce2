package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

@Data
public class AddItemRequest {
        //@NotNull(message = "Username cannot be null")
        private String username;
        //@NotNull(message = "Product id cannot be null")
        private String productId;
        //@NotNull(message = "Quantity cannot be null")
       // @Min(value = 1, message = "Quantity must be greater than or equal to 1")
        private int quantityOfProduct;

}
