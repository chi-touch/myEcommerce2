package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.*;

import africa.semicolon.myEcommerce2.data.model.Payment;
import africa.semicolon.myEcommerce2.data.repositories.OrderRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.data.repositories.UserRepository;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.myEcommerce2.data.model.ProductType.UTENSILS;
import static africa.semicolon.myEcommerce2.data.model.Role.ADMIN;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {
    RegisterRequest request;
    LoginRequest loginRequest;
    @Autowired
    UserService userService;


    @Autowired
    PaymentService paymentService;
    @Autowired
    OrderService orderService;
    OrderRequest  orderRequest;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    ProductRepository productRepository;

    Payment payment;


    Product product;
    @BeforeEach
    public void setUp(){
        userRepository.deleteAll();
        productRepository.deleteAll();
        request = new RegisterRequest();
        loginRequest = new LoginRequest();
    }

    @Test

    public void testToRegisterUser(){
        request.setRole(Role.CUSTOMER);
        request.setFirstName("chichi");
        request.setLastName("dave");
        request.setPassword("1234");
        request.setUsername("ami");
        request.setCountry("china");
        request.setState("Lagos");
        request.setHouseNumber("No2 sabo");
        request.setStreet("sabo");


        RegisterResponse registerResponse =  userService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();
        assertEquals(1, userService.count());
    }


    @Test
    public void testToRegisterTwoUser(){

        RegisterRequest request2 = new RegisterRequest();
        request2.setRole(Role.CUSTOMER);
        request2.setFirstName("chichi3");
        request2.setLastName("dave3");
        request2.setPassword("1237");
        request2.setUsername("ami3");



        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chich");
        registerRequest1.setLastName("daved");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div");

        RegisterResponse registerResponse1 =  userService.register(registerRequest1);
        RegisterResponse registerResponse2 =  userService.register(request2);

        assertThat(registerResponse1).isNotNull();
        assertThat(registerResponse2).isNotNull();


        assertThat(registerResponse1.getMessage()).isNotNull();
        assertThat(registerResponse2.getMessage()).isNotNull();

        assertEquals(2,userService.count());
    }

    @Test
    public void testToLoginUser(){

        request.setRole(Role.CUSTOMER);
        request.setFirstName("chichi");
        request.setLastName("dave");
        request.setPassword("1234");
        request.setUsername("ami");
        request.setCountry("china");
        request.setState("Lagos");
        request.setHouseNumber("No2 sabo");
        request.setStreet("sabo");
        userService.register(request);


        loginRequest.setUsername("ami");
        loginRequest.setPassword("1234");
        LoginResponse loginResponse = userService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isNotNull();
    }

    @Test
    public void testToFindByUserName() {
        RegisterRequest requestf = new RegisterRequest();
        requestf.setRole(Role.CUSTOMER);
        requestf.setFirstName("chichiu");
        requestf.setLastName("daveg");
        requestf.setPassword("1239");
        requestf.setUsername("amid");
        requestf.setCountry("chinam");
        requestf.setState("Lagost");
        requestf.setHouseNumber("No2 saboi");
        requestf.setStreet("sabop");

        userService.register(requestf);
        assertEquals("amid", userService.findByUsername("amid").getUsername());
    }

    @Test
    public void testToDeleteAll(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();
        userService.deleteAll();
        assertEquals(0,userService.count());
    }

    @Test
    public void testToSearchProduct(){
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("cup");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        userService.create(createProductRequest);

        List<Product> products = userService.searchProduct("cup");
        assertThat(products).isNotNull();
        assertThat(products).isNotEmpty();


        assertEquals("cup",userService.searchProduct("cup").get(0).getProductName());
    }

    @Test
    public void testToOrderProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        List<User> userList = userService.getAllUsers();
        User user = userList.get(0);
        OrderRequest orderRequest = new OrderRequest();

        List<Product> productList = new ArrayList<>(List.of(new Product()));
        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getId(), productList);

        OrderResponse orderResponse = new OrderResponse();
        orderResponse.setMessage("Your order has been successfully placed");
        assertThat(orderResponse.getMessage()).isNotNull();
        assertThat(order).isNotNull();
    }

    @Test
    public void testForPayment(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        List<User> userList = userService.getAllUsers();
        User user = userList.get(0);
        OrderRequest orderRequest = new OrderRequest();

        List<Product> productList = new ArrayList<>(List.of(new Product()));
        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getId(), productList);

        PaymentAtDeliveryRequest paymentRequest = new PaymentAtDeliveryRequest();
        paymentRequest.setStatus(TransactionStatus.SUCCESS);
        paymentRequest.setAmount(BigDecimal.valueOf(2000));
        paymentRequest.setDeliveryId(user.getId());
        PaymentDeliveryResponse paymentResponse =  paymentService.atDelivery(paymentRequest);
        paymentResponse.setMessage("Thank you");

        assertEquals("Thank you", paymentResponse.getMessage());
    }

    @Test
    public void testToTransfer(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        List<User> userList = userService.getAllUsers();
        User user = userList.get(0);

        OrderRequest orderRequest = new OrderRequest();
        List<Product> productList = new ArrayList<>(List.of(new Product()));
        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getId(), productList);

        TransferRequest transferRequest = new TransferRequest();
        transferRequest.setPin("pin");
        transferRequest.setAmount(BigDecimal.valueOf(2000));
        transferRequest.setTo("me");
        transferRequest.setFrom("div2");
        transferRequest.setDescription("kitchen tools");

        TransferResponse transferResponse =  paymentService.transfer(transferRequest);
        transferResponse.setMessage("Transfer successful");

        assertEquals("Transfer successful", transferResponse.getMessage());

    }



}