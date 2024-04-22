package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

@Data
public class RemoveItemRequest {

    private String username;
    //@NotNull(message = "Product id cannot be null")
    private String productId;
}
