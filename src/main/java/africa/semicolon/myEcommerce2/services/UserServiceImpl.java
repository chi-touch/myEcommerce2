package africa.semicolon.myEcommerce2.services;
import africa.semicolon.myEcommerce2.data.model.*;
import africa.semicolon.myEcommerce2.data.repositories.OrderRepository;
import africa.semicolon.myEcommerce2.data.repositories.PaymentRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.data.repositories.UserRepository;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;




@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

   @Autowired
    private UserRepository userRepository;

   private final OrderService orderService;

   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private PaymentRepository paymentRepository;

   @Autowired
   private ProductRepository productRepository;

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
    public CreateProductResponse create(CreateProductRequest createProduct) {
        if (productAlreadyExist(createProduct.getProductName())){
            throw new ProductAlreadyExistException("this product already exist");
        }


        Product product = Mapper.productMapper(createProduct);
        productRepository.save(product);
        CreateProductResponse createProductResponse = new CreateProductResponse();
        createProductResponse.setMessage("product created successfully");
        return createProductResponse;
    }

    private boolean productAlreadyExist(String productName) {
        return productRepository.findByProductName(productName) != null;

    }

    @Override
    public Product findProductByName(String productName) {
        return productRepository.searchByProductName(productName);
    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public PaymentResponse payment(PaymentAtDeliveryRequest paymentAtDeliveryRequest) {
        if (paymentExist(paymentAtDeliveryRequest.getAmount())< 0){
            throw new InvalidInputEnteredException("Invalid amount");
        }
        Payment payment = Mapper.paymentMapper(paymentAtDeliveryRequest);
        paymentRepository.save(payment);

        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setMessage("Payment is successful");
        return paymentResponse;
    }
    private int paymentExist(BigDecimal amount){
        return paymentRepository.paidAmount(amount);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }


    @Override
    public OrderResponse order(OrderRequest orderRequest) {
        Order order = Mapper.orderMapper(orderRequest);
        orderRepository.save(order);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setMessage("Order placed successfully");
        return orderResponse;
    }



    @Override
    public List<Product> addProduct(Product product) {
        return null;
    }


    @Override
    public List<Product> searchProduct(String productName) {
        List<Product> productList = productService.getAllProduct();
        List<Product> search = new ArrayList<>();
        String selected = productName.trim();
        for (Product product : productList) {
            if (product.getProductName().equals(selected)) {
                search.add(product);
            }
        }
        return search;
    }


}
