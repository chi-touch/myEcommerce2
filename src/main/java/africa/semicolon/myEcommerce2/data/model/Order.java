package africa.semicolon.myEcommerce2.data.model;

import africa.semicolon.myEcommerce2.data.model.user.Address;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class Order {

    @Id
    private Long id;
//    @ManyToMany
//    @Convert(converter = Product.class)
//    private List<Product> productList = new ArrayList<>();
    private BigDecimal price;
    private String productStatus;
    private String deliveryDate = String.valueOf(LocalDate.now());
    @OneToOne(cascade = CascadeType.ALL,fetch =FetchType.EAGER)
    private Address address;


}
