package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ProductType;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AddProductRequest {
    private String productName;
//    private Order order;
    private int productQuantity;


   // @NotNull(message = "Username cannot be null")
    private String username;
   // @NotNull(message = "Product name cannot be null")
   // @NotBlank(message = "Product name cannot be blank")
   // private Product productName;
  //  @NotNull(message = "Price cannot be null")
  //  @Min(value = 1, message = "Price must be greater than or equal to 1")
    private BigDecimal price;
  //  @NotNull(message = "Category cannot be null")
    private ProductType category;
   // @NotNull(message = "Category cannot be null")
  //  @NotBlank(message = "Category cannot be blank")
    private String description;
  //  @NotNull(message = "Quantity cannot be null")
  //  @Min(value = 1, message = "Quantity must be greater than or equal to 1")
    //private int quantity;

}
