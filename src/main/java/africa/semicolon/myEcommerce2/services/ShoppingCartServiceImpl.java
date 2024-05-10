package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.*;

import africa.semicolon.myEcommerce2.data.repositories.EcommerceUserRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.data.repositories.ShoppingCartRepository;
import africa.semicolon.myEcommerce2.dto.request.AddItemRequest;
import africa.semicolon.myEcommerce2.dto.request.RemoveItemRequest;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.ProductDoesExistException;
import africa.semicolon.myEcommerce2.exceptions.ShoppingCartIsEmptyException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    ProductService productService;

    @Autowired
    ShoppingCartRepository shoppingCartRepository;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    EcommerceUserRepository userRepository;


    @Override
    public ShoppingCart addItemToCart(AddItemRequest addItemRequest) {
        if (addItemRequest.getUsername() == null){
            throw new InvalidInputEnteredException("This username is not valid");
        }

        if (addItemRequest.getProductName() == null){
            throw new ProductDoesExistException("this product does not exist");
        }

        Optional<Product> optionalProduct = productRepository.findById(addItemRequest.getProductId());
        Product foundProduct = null;
        if (optionalProduct.isPresent()) foundProduct = optionalProduct.get();
        else throw new ProductDoesExistException("this product does not exist");
       // Product foundProduct = productService.findProductByName(addItemRequest.getProductName());

        EcommerceUser foundUser = userRepository.findByUsername(addItemRequest.getUsername());
        ShoppingCart foundCart = foundUser.getCart();

        addProduct(foundProduct,foundCart);
        EcommerceUser savedUser = userRepository.save(foundUser);
//        ShoppingCart newShoppingCartty = shoppingCartRepository.save(foundCart);

        return foundCart;

    }

    public void addProduct(Product foundProduct,ShoppingCart foundCart) {

        Item item = new Item();
        item.setProductId(foundProduct.getProductId());
        item.setProductName(foundProduct.getProductName());
//        item.setQuantityOfProduct(1);
        item.setPrice(foundProduct.getPrice());
        foundCart.addProductToCart(item);
    }

}
