package africa.semicolon.myEcommerce2.services;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.repositories.UserRepository;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginResponse;
import africa.semicolon.myEcommerce2.dto.response.OrderResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static africa.semicolon.myEcommerce2.data.model.Role.CUSTOMER;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

   @Autowired
    private UserRepository userRepository;

   private final OrderService orderService;

   private final ProductService productService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userAlreadyExist(registerRequest.getUsername())){
            throw new UserAlreadyExistException("this user already exist");
        }
       User user = Mapper.mapper(registerRequest);
        userRepository.save(user);
        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setMessage("Registration is successful");
        return registerResponse;
    }
    private boolean userAlreadyExist(String username){
        return userRepository.findByUsername(username) != null;
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User foundUser = userRepository.findByUsername(loginRequest.getUsername());
        foundUser.setLocked(false);
        userRepository.save(foundUser);
        if (isValidUsernameAndPassword(username, password)) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Login successful");
            return response;
        } else {
            throw new InvalidInputEnteredException("Invalid username or password");
        }
    }
    private boolean isValidUsernameAndPassword(String username, String password) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public OrderResponse order(OrderRequest orderRequest) {
        return null;
    }

    @Override
    public List<Product> addProduct(Product product) {
        return null;
    }

    @Override
    public List<Product> searchProduct(String productName) {

        return productService.getAllProduct()
                .stream()
                .filter(product -> product.getProductName().contains(productName.trim()))
                .collect(Collectors.toList());
    }


}
