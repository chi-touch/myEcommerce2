package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.*;

import africa.semicolon.myEcommerce2.data.model.Payment;
import africa.semicolon.myEcommerce2.data.repositories.OrderRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.data.repositories.EcommerceUserRepository;
import africa.semicolon.myEcommerce2.data.repositories.ShoppingCartRepository;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import lombok.var;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.myEcommerce2.data.model.ProductType.*;
import static africa.semicolon.myEcommerce2.data.model.Role.ADMIN;

import static africa.semicolon.myEcommerce2.data.model.Role.CUSTOMER;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {


    private EcommerceUser ecommerceUser;
    RegisterRequest request;
    LoginRequest loginRequest;
    @Autowired
    UserService userService;

    @Autowired
    ShoppingCartService shoppingCartService;
    RegisterResponse registerResponse = new RegisterResponse();

    @Autowired
    PaymentService paymentService;


    @Autowired
    ProductService productService;
    @Autowired
    OrderService orderService;


    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EcommerceUserRepository userRepository;

    @Autowired
    ProductRepository productRepository;



    @Autowired
    ShoppingCartRepository shoppingCartRepository;

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

//    @Test
//    public void testToSearchProduct(){
//        RegisterRequest registerRequest1 = new RegisterRequest();
//        registerRequest1.setRole(ADMIN);
//        registerRequest1.setFirstName("chichi2");
//        registerRequest1.setLastName("dave2");
//        registerRequest1.setPassword("1235");
//        registerRequest1.setUsername("div2");
//        RegisterResponse registerResponse = userService.register(registerRequest1);
//
//        CreateProductRequest createProductRequest = new CreateProductRequest();
//        createProductRequest.setProductName("cup");
//        createProductRequest.setProductType(UTENSILS);
//        createProductRequest.setPrice(BigDecimal.valueOf(1000));
//        createProductRequest.setDescription("kitchen tools");
//        productService.create(createProductRequest);
//       // productService.create(createProductRequest);
//
//        SearchProductRequest searchRequest = new SearchProductRequest();
//        searchRequest.setProductName("cup");
//        searchRequest.setUsername("div2");
//
//        var ViewAllProductResponse = productService.searchProductByName("cup");
//
//        assertEquals(1,userService.searchProductByName("cup").size());
//    }

    @Test
    public void testToOrderProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        List<EcommerceUser> userList = userService.getAllUsers();
        EcommerceUser user = userList.get(0);
        OrderRequest orderRequest = new OrderRequest();

        List<Product> productList = new ArrayList<>();
        //orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getUserId(), productList);

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
        List<EcommerceUser> userList = userService.getAllUsers();
        EcommerceUser user = userList.get(0);
        OrderRequest orderRequest = new OrderRequest();

        List<Product> productList = new ArrayList<>();
//        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getUserId(), productList);

        PaymentAtDeliveryRequest paymentRequest = new PaymentAtDeliveryRequest();
        paymentRequest.setStatus(TransactionStatus.SUCCESS);
        paymentRequest.setAmount(BigDecimal.valueOf(2000));
        paymentRequest.setDeliveryId(user.getUserId());
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
        List<EcommerceUser> userList = userService.getAllUsers();
        EcommerceUser user = userList.get(0);

        OrderRequest orderRequest = new OrderRequest();
        List<Product> productList = new ArrayList<>();
//        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getUserId(), productList);

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

    @Test
    public void testToCreatProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("cup");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        createProductRequest.setUserId(registerResponse.getUserId());


        CreateProductResponse createProductResponse = productService.create(createProductRequest);

        assertThat(createProductResponse).isNotNull();
        assertThat(createProductResponse.getMessage()).isNotNull();
        assertEquals("cup", productService.findProductByName("cup").getProductName());
    }

    @Test
    public void testToCreatMoreProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("cup");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        createProductRequest.setUserId(registerResponse.getUserId());
        CreateProductResponse createProductResponse = productService.create(createProductRequest);

        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setProductName("spoon");
        createProductRequest1.setProductType(UTENSILS);
        createProductRequest1.setPrice(BigDecimal.valueOf(500));
        createProductRequest1.setDescription("kitchen tools");
        createProductRequest1.setUserId(registerResponse.getUserId());
        CreateProductResponse createProductResponse1 = productService.create(createProductRequest1);

        assertEquals("cup", userService.findProductByName("cup").getProductName());
        assertEquals("spoon", userService.findProductByName("spoon").getProductName());
    }


    @Test
    public void testToAddProduct() {
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);

        AddProductResponse addProductResponse = getAddProductResponse("cup", registerResponse);
        assertThat(addProductResponse.getMessage()).isNotNull();

        Product myProduct = productService.findProductByName("cup");
        assertEquals("cup", myProduct.getProductName());
        //assertEquals(1, myProduct.getProductQuantity());
       // assertEquals(UTENSILS, myProduct.getProductType());
       // assertEquals(BigDecimal.valueOf(1000), myProduct.getPrice());
        //assertEquals("kitchen tools", myProduct.getDescription());
    }

    @Test
    public void testToRemoveProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("cup");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        createProductRequest.setUserId(registerResponse.getUserId());

        CreateProductResponse createProductResponse = productService.create(createProductRequest);
        assertEquals(1,productService.count());

        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setProductName("spoon");
        createProductRequest1.setProductType(UTENSILS);
        createProductRequest1.setPrice(BigDecimal.valueOf(500));
        createProductRequest1.setDescription("kitchen tools");
        createProductRequest1.setUserId(registerResponse.getUserId());
        CreateProductResponse createProductResponse1 = productService.create(createProductRequest1);

        assertEquals(2,productService.count());
        RemoveProductResponse removeProductRequest = userService.removeProduct("cup");
        assertThat(removeProductRequest.getMessage());
        assertEquals(1,productService.count());

    }

    @Test
    public void testToViewCart(){

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
       RegisterResponse registerResponse1 = userService.register(registerRequest1);

        getAddProductResponse("cup", registerResponse);


        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("slippers");
        createProductRequest.setProductType(FASHION);
        createProductRequest.setProductQuantity(1);
        createProductRequest.setDescription("foot wears");
        createProductRequest.setPrice(BigDecimal.valueOf(2000));
        createProductRequest.setUserId(registerResponse.getUserId());
        userService.create(createProductRequest);


//        AddProductRequest addProductRequest1 = new AddProductRequest();
//        addProductRequest1.setUsername("div2");
//        addProductRequest1.setProductQuantity(2);
//        addProductRequest1.setProductType(FASHION);
//        addProductRequest1.setProductName("slippers");
//        userService.addProduct(addProductRequest1);


        ViewCartRequest viewCartRequest1 = new ViewCartRequest();
        viewCartRequest1.setUsername("div2");

        userService.viewCart(viewCartRequest1);
    }

    @Test
    public void testForSignUpException(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(CUSTOMER);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("mark");
        userService.register(registerRequest1);
        assertThrows(UserAlreadyExistException.class,() -> userService.register(registerRequest1));


    }

    @Test
    public void testAddItem(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse response = userService.register(registerRequest1);

        AddProductResponse addProductResponse = getAddProductResponse("cup", response);

        RegisterResponse registerResponse1 = new RegisterResponse();
        AddItemRequest addItemRequest = getAddItemRequest(addProductResponse);
        shoppingCartService.addItemToCart(addItemRequest);

        assertEquals(1,userService.getUserCart(response.getUserId()).size());

    }


    @Test
    public void testToAddSeperateProduct(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse response  = userService.register(registerRequest1);

        AddProductResponse addProductResponseOne = getAddProductResponse("cup", response);

        AddProductResponse addProductResponseTwo = getAddProductResponse("pot", response);
        AddProductResponse addProductResponseThree =getAddProductResponse("Sugar", response);

        AddItemRequest addItemRequest = getAddItemRequest(addProductResponseOne);
        shoppingCartService.addItemToCart(addItemRequest);

        AddItemRequest addItemRequestTwo = getAddItemRequest(addProductResponseTwo);
        shoppingCartService.addItemToCart(addItemRequestTwo);

        AddItemRequest addItemRequestThree = getAddItemRequest(addProductResponseThree);
        shoppingCartService.addItemToCart(addItemRequestThree);

        assertEquals(3,userService.getUserCart(response.getUserId()).size());

    }

    private static AddItemRequest getAddItemRequest(AddProductResponse addProductResponse) {
        AddItemRequest addItemRequest = new AddItemRequest();
        addItemRequest.setUsername("div2");
        addItemRequest.setProductName("cup");
        addItemRequest.setQuantityOfProduct(2);
        addItemRequest.setProductId(addProductResponse.getProductId());
        return addItemRequest;
    }

    private AddProductResponse getAddProductResponse(String productName, RegisterResponse registerResponse) {
        AddProductRequest addProductRequest = new AddProductRequest();
        addProductRequest.setProductName(productName);
        addProductRequest.setProductType(UTENSILS);
        addProductRequest.setDescription("kitchen tools");
        addProductRequest.setUserId(registerResponse.getUserId());
        AddProductResponse addProductResponse = userService.addProduct(addProductRequest);
        return addProductResponse;
    }


}