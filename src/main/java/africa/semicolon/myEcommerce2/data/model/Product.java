package africa.semicolon.myEcommerce2.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

import java.math.BigDecimal;
@Data
@Entity
public class Product {
    @Id
    private Long id;
    private String productName;
    private ProductType productType;
    private BigDecimal price;

}
