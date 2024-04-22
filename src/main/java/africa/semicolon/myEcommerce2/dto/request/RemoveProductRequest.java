package africa.semicolon.myEcommerce2.dto.request;


import lombok.Data;

@Data
public class RemoveProductRequest {
   // @NotNull(message = "Username cannot be null")
    private String username;
   // @NotNull(message = "Product ID cannot be null")
    private String productId;
}
