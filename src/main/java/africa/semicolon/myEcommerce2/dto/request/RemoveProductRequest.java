package africa.semicolon.myEcommerce2.dto.request;


import africa.semicolon.myEcommerce2.data.model.Product;
import lombok.Data;

@Data
public class RemoveProductRequest {
    private String productName;
    private String productId;
}
