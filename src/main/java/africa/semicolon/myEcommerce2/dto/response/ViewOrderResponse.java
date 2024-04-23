package africa.semicolon.myEcommerce2.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ViewOrderResponse {
    private String username;
    private String orderId;
    private int numberOfItems;
    private List<GetItemResponse> items = new ArrayList<>();
    private BigDecimal totalPrice;
    private String dateOfOrder = orderDate();

    private String orderDate(){
        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy hh:mm:ss");
        return dateTime.format(dateTimeFormatter);

    }


}
