package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.sql.RowSet;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data

@Document
public class Order {

    @Id
    private String id;

   private List<Product> productList = new ArrayList<>();
    private BigDecimal amount;
    private String deliveryDate = deliveryAt();
    private String productName;
    private int productQuantity;
    private EcommerceUser buyer;
    private CustomerInformation customerInformation;
    private  String deliveryAt(){
        LocalDateTime  time = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return time.format(dateTimeFormatter);
   }
    private OrderStatus orderStatus;
    private Address address;
    //private Address deliveryAddress = new Address();

    private String createTime = createdAt();

    private String createdAt(){
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return date.format(dateTimeFormatter);
    }




}
