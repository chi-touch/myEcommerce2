package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.ProductType;
import africa.semicolon.myEcommerce2.data.model.Role;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.math.BigDecimal;
@Data
public class CreateProductRequest {

    private String productName;
    private ProductType productType;
    private BigDecimal price;
    private String description;
    private String userId;
    private int productQuantity;
    //private String username;
   //private Role role;


}
