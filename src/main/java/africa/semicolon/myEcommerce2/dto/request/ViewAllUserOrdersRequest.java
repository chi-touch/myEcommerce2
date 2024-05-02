package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.Order;
import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ViewAllUserOrdersRequest {
    private String username;
    private Order order;

}
