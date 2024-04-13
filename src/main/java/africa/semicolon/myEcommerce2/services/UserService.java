package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);

    long count();
    LoginResponse login(LoginRequest loginRequest);

    User findByUsername(String username);

    OrderResponse order(OrderRequest orderRequest);

    List<Product> addProduct(Product product);

    List<Product> searchProduct(String productName);

    List<User> getAllUsers();

    void deleteAll();

    CreateProductResponse create(CreateProductRequest createProduct);
    Product findProductByName(String productName);

    List<Product>getAllProduct();

    PaymentResponse payment(PaymentAtDeliveryRequest paymentAtDeliveryRequest);
}
