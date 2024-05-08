package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;

import africa.semicolon.myEcommerce2.data.model.Item;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ShoppingCart;
import africa.semicolon.myEcommerce2.data.repositories.EcommerceUserRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.data.repositories.ShoppingCartRepository;
import africa.semicolon.myEcommerce2.dto.request.AddItemRequest;
import africa.semicolon.myEcommerce2.dto.request.RemoveItemRequest;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
  EcommerceUserRepository userRepository;


    @Override
    public ShoppingCart addItemToCart(AddItemRequest addItemRequest) {
        if (addItemRequest.getUsername() == null){
            throw new InvalidInputEnteredException("This username is not valid");
        }

        Product foundProduct = productService.findProductByName(addItemRequest.getProductName());

        EcommerceUser foundUser = userRepository.findByUsername(addItemRequest.getUsername());
        ShoppingCart foundCart = foundUser.getCart();
        addProduct(foundProduct,foundUser,foundCart);
        ShoppingCart newShoppingCartty = shoppingCartRepository.save(foundCart);

        return newShoppingCartty;

    }

    public void addProduct(Product foundProduct,EcommerceUser foundUser,ShoppingCart foundCart) {

        Item item = new Item();
        item.setProductId(foundProduct.getId());
        item.setProductName(foundProduct.getProductName());
        item.setQuantityOfProduct(foundProduct.getProductQuantity());
        item.setPrice(foundProduct.getPrice());
        foundCart.addProductToCart(item);
    }

}
