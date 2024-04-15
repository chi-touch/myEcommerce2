package africa.semicolon.myEcommerce2.dto.request;




import africa.semicolon.myEcommerce2.data.model.OrderStatus;
import africa.semicolon.myEcommerce2.data.model.Product;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class OrderRequest {
    private String state;
    private String country;
    private String street;
    private String houseNumber;

    @DBRef
    private List<Product> productList;
    private String deliveryDate = deliveryAt();
    private String deliveryAt(){
        LocalDateTime time = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return time.format(formatter);
    }
    private OrderStatus orderStatus;
}
