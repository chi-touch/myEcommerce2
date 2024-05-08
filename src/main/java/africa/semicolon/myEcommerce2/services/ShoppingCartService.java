package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.data.model.ShoppingCart;
import africa.semicolon.myEcommerce2.dto.request.AddItemRequest;
import africa.semicolon.myEcommerce2.dto.request.RemoveItemRequest;

public interface ShoppingCartService {

    ShoppingCart addItemToCart(AddItemRequest addItemRequest);
}
