package africa.semicolon.myEcommerce2.data.model;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String productName;
    private ProductType productType;
    private BigDecimal price;
    private String description;

//    @ManyToMany(mappedBy = "productList")
//    private List<Order> orders = new ArrayList<>();



}
