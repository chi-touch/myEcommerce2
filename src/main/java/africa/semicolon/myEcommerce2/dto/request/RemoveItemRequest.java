package africa.semicolon.myEcommerce2.dto.request;

import lombok.Data;

@Data
public class RemoveItemRequest {

    private String itemName;
    private String productId;
}
