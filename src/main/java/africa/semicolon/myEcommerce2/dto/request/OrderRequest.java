package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.Address;
import africa.semicolon.myEcommerce2.data.model.OrderStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Setter
@Getter
public class OrderRequest {
    private String state;
    private String country;
    private String street;
    private String houseNumber;
    private BigDecimal amount;
    private String productStatus;
    private String deliveryDate = String.valueOf(LocalDate.now());
    private OrderStatus orderStatus;
}
