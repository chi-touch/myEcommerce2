package africa.semicolon.myEcommerce2.dto.request;




import africa.semicolon.myEcommerce2.data.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Setter
@Getter
public class OrderRequest {
    private String state;
    private String country;
    private String street;
    private String houseNumber;
    //private BigDecimal amount;
    //private String productStatus;
//    private String deliveryDate = deliveryAt();
//    private String deliveryAt(){
//        LocalDateTime time = LocalDateTime.now();
//        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
//        return time.format(formatter);
//    }
    private OrderStatus orderStatus;
}
