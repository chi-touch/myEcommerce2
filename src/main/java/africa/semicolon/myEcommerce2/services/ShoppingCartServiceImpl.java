package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ShoppingCart;
import africa.semicolon.myEcommerce2.dto.request.AddItemRequest;
import africa.semicolon.myEcommerce2.dto.request.RemoveItemRequest;
import africa.semicolon.myEcommerce2.exceptions.ShoppingCartIsEmptyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingCartServiceImpl implements CartService{

    @Autowired
    ProductService productService
    @Override
    public ShoppingCart addToCartWith(AddItemRequest addItemRequest, EcommerceUser user) {
//        Product myProduct = productService.findProductByName(addItemRequest.getProductId());
//        validate(addItemRequest.getQuantityOfProduct());

        return  null;
    }

//    private void validate(int cart) {
//        if (cart.isEmpty()) throw new ShoppingCartIsEmptyException("Your cart is empty");
//    }

    @Override
    public ShoppingCart removeFromCartWith(RemoveItemRequest removeItemRequest, EcommerceUser user) {
        return null;
    }
}
