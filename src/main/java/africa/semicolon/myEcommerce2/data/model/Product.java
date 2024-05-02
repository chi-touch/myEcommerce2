package africa.semicolon.myEcommerce2.data.model;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class Product {
    @Id
    private String id;
    private String productName;
    private ProductType productType;
    private BigDecimal price;
    private String description;

    private List<Order> orders = new ArrayList<>();

    private String createTime = createdAt();

    private String createdAt(){
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return date.format(dateTimeFormatter);
    }


    private int productQuantity;
}
