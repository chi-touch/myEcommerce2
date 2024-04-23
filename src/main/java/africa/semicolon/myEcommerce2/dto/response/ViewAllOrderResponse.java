package africa.semicolon.myEcommerce2.dto.response;

import java.util.ArrayList;
import java.util.List;

public class ViewAllOrderResponse {
    private String username;
    private List<ViewOrderResponse> orders = new ArrayList<>();
}
