package africa.semicolon.myEcommerce2.dto.response;

import africa.semicolon.myEcommerce2.data.model.ShoppingCart;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ViewCartResponse {
    private String username;
    private ShoppingCart shoppingCart;
}
