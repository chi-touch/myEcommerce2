package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginResponse;
import africa.semicolon.myEcommerce2.dto.response.OrderResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;

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
}
