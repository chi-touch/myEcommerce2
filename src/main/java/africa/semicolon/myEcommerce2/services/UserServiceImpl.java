package africa.semicolon.myEcommerce2.services;
import africa.semicolon.myEcommerce2.data.model.*;
import africa.semicolon.myEcommerce2.data.model.Payment;
import africa.semicolon.myEcommerce2.data.repositories.*;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;
import africa.semicolon.myEcommerce2.exceptions.*;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static africa.semicolon.myEcommerce2.data.model.TransactionStatus.SUCCESS;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{

   @Autowired
    private EcommerceUserRepository userRepository;

   @Autowired
   private OrderService orderService;

   @Autowired
   private OrderRepository orderRepository;

   @Autowired
   private PaymentRepository paymentRepository;

   @Autowired
   private ProductRepository productRepository;

   @Autowired
   private CreditCardInformationRepository creditCardInformationRepository;

   @Autowired
   private CustomerInformationRepository customerInformationRepository;

   @Autowired
   private ShoppingCartRepository shoppingCartRepository;

   @Autowired
   private ProductService productService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        if (userAlreadyExist(registerRequest.getUsername())){
            throw new UserAlreadyExistException("this user already exist");
        }
       EcommerceUser user = Mapper.mapper(registerRequest);
        EcommerceUser savedUser = userRepository.save(user);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setMessage("Registration is successful");
        registerResponse.setUserId(savedUser.getUserId());
        return registerResponse;
    }
    private boolean userAlreadyExist(String username){
        return userRepository.existsByUsername(username);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        EcommerceUser foundUser = userRepository.findByUsername(loginRequest.getUsername());
        foundUser.setLocked(false);
        userRepository.save(foundUser);
        if (isValidUsernameAndPassword(username, password)) {
            LoginResponse response = new LoginResponse();
            response.setUsername(loginRequest.getUsername());
            response.setLoggedIn(true);
            response.setMessage("Login successful");
            return response;
        } else {
            throw new InvalidInputEnteredException("Invalid username or password");
        }
    }

    @Override
    public LogOutResponse logOut(LogOutRequest logOutRequest) {
        if (logOutRequest.getUsername() == null){
            throw new InvalidInputEnteredException("Invalid username or password");
        }
        EcommerceUser foundUser = findByUsername(logOutRequest.getUsername());

        foundUser.setLocked(true);
        LogOutResponse logOutResponse = new LogOutResponse();
        logOutResponse.setMessage("Logout successful");
        return logOutResponse;
    }

    @Override
    public ShoppingCart addItemToCart(AddItemRequest addItemRequest) {
        if (addItemRequest.getUsername() == null) {
            throw new InvalidInputEnteredException("This username is not valid");
        }
        EcommerceUser foundUser = userRepository.findByUsername(addItemRequest.getUsername());

        if (addItemRequest.getProductName() == null){
            throw new ProductDoesExistException("this product does not exist");
        }

        Product foundProduct = new Product();
        foundProduct.setProductId(addItemRequest.getProductId());
        foundProduct.setUsername(addItemRequest.getUsername());
        foundProduct.setProductName(addItemRequest.getProductName());
        foundProduct.setProductQuantity(addItemRequest.getQuantityOfProduct());
        foundProduct.setProductType(addItemRequest.getProductType());
        foundProduct.setDescription(addItemRequest.getDescription());
        foundProduct.setPrice(addItemRequest.getPrice());
        productRepository.save(foundProduct);

      ShoppingCart foundCart = foundUser.getCart();
      addItem(foundProduct,foundUser,foundCart);
      ShoppingCart newShoppingCarry = shoppingCartRepository.save(foundCart);
      userRepository.save(foundUser);

       return newShoppingCarry;
    }
    public void addItem(Product foundProduct,EcommerceUser foundUser,ShoppingCart foundCart) {

        Item item = new Item();
        item.setProductId(foundProduct.getProductId());
        item.setProductName(foundProduct.getProductName());
        item.setQuantityOfProduct(foundProduct.getProductQuantity());
        item.setPrice(foundProduct.getPrice());
        foundCart.addProductToCart(item);
    }


    private boolean isValidUsernameAndPassword(String username, String password) {
        List<EcommerceUser> userList = userRepository.findAll();
        for (EcommerceUser user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public List<EcommerceUser> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public List<Product> searchProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    @Override
    public CreateProductResponse create(CreateProductRequest createProduct) {
//        if (productAlreadyExist(createProduct.getProductName())){
//            throw new ProductAlreadyExistException("this product already exist");
//        }
        Product product = Mapper.productMapper(createProduct);
        productRepository.save(product);
        CreateProductResponse createProductResponse = new CreateProductResponse();
        createProductResponse.setMessage("product is created successfully");
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
    public PaymentDeliveryResponse delivery(PaymentAtDeliveryRequest paymentAtDeliveryRequest) {

        if (paymentAtDeliveryRequest == null) {
            throw new InvalidPaymentRequestException("Payment request is null");
        }
        if (paymentAtDeliveryRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0) {
            throw new InvalidPaymentRequestException("Payment amount should be greater than zero");
        }
        if (paymentAtDeliveryRequest.getDeliveryId() == null || paymentAtDeliveryRequest.getDeliveryId().isEmpty()) {
            throw new InvalidPaymentRequestException("Delivery ID is required");
        }

        Payment payment = Mapper.paymentDeliveryMapper(paymentAtDeliveryRequest);
        paymentRepository.save(payment);

        PaymentDeliveryResponse deliveryResponse = new PaymentDeliveryResponse();
        deliveryResponse.setMessage("Thank you");
        return deliveryResponse;
    }

    @Override
    public List<ShoppingCart>getItems() {
        return shoppingCartRepository.findAll();
    }

    @Override
    public List<Item> getUserCart(String userId) {
      EcommerceUser foundUser = userRepository.findById(userId).orElseThrow(()-> new UserNameNotFoundException("this user does not exist"));
      return foundUser.getCart().getItems();

    }


    public TransferResponse transfer(TransferRequest transferRequest) {
        BigDecimal amount = transferRequest.getAmount();
        if (amount.compareTo(BigDecimal.ZERO) < 0) {
            throw new NegativeAmountException("Amount should be positive");
        }

        if (invalidTransferRequest(transferRequest)) {
            throw new InvalidTransferRequestException("Invalid transfer request");
        }

        boolean transferSuccess = performTransfer(transferRequest);

        Payment payment = new Payment();
        payment.setBalance(transferRequest.getAmount());
        payment.setAccountNumber(transferRequest.getTo());
        payment.setAmount(transferRequest.getAmount());
        payment.setStatus(SUCCESS);
        paymentRepository.save(payment);
        TransferResponse response = new TransferResponse();
        if (transferSuccess) {
            response.setMessage("Transfer successful");
            response.setAmountPaid(transferRequest.getAmount());
        } else {
            response.setMessage("Transfer failed");
            response.setAmountPaid(BigDecimal.ZERO);
        }
        return response;
    }

    private boolean invalidTransferRequest(TransferRequest transferRequest) {
        return transferRequest.getFrom() == null ||
                transferRequest.getTo() == null ||
                transferRequest.getAmount() == null ||
                transferRequest.getPin() == null ||
                transferRequest.getAmount().compareTo(BigDecimal.ZERO) <= 0;
    }

    private boolean performTransfer(TransferRequest transferRequest) {
        System.out.println("Transferring " + transferRequest.getAmount() + " from " + transferRequest.getFrom() +
                " to " + transferRequest.getTo() + " with description: " + transferRequest.getDescription());
        return true;
    }

    @Override
    public EcommerceUser findByUsername(String username) {
        EcommerceUser myUser = userRepository.findByUsername(username);
        if (myUser.getUsername() != null){
             return  myUser;
        }
        throw new UserNameNotFoundException("User not found");
    }

    private String ifUserNameExist(String username){
        return userRepository.getByUsername(username);
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
    public AddProductResponse addProduct(AddProductRequest addProductRequest){

        EcommerceUser foundUser = userRepository.findById(addProductRequest.getUserId()).orElseThrow(()-> new UserNameNotFoundException("User not found"));
        if(!foundUser.getRole().equals(Role.ADMIN)){
            throw new InvalidInputEnteredException("You are not allowed to add product");
        }

       Product product = new Product();

        product.setProductName(addProductRequest.getProductName());
        product.setProductQuantity(addProductRequest.getProductQuantity());
        product.setPrice(addProductRequest.getPrice());
        product.setProductType(addProductRequest.getProductType());
        product.setDescription(addProductRequest.getDescription());

       Product foundProduct = productRepository.save(product);


        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setMessage("Product was added successfully");
        addProductResponse.setProductId(foundProduct.getProductId());
        return addProductResponse;

    }


    //        Order order = new Order();
//        order.setProductName(addProductRequest.getProductName());
//        order.setProductQuantity(addProductRequest.getProductQuantity());
//        orderRepository.save(order);
    @Override
    public RemoveProductResponse removeProduct(String productId) {
        if (productId == null) {
            throw new InvalidInputEnteredException("This product does not exist");
        }
        Product product = productRepository.deleteByProductName(productId);
        RemoveProductResponse removeResponse = new RemoveProductResponse();
        removeResponse.setMessage("Product was successfully removed");
        return removeResponse;
    }
//
//    @Override
//    public List<ShoppingCart> viewCart(ViewCartRequest viewCartRequest) {
//         List <ShoppingCart> shoppingCart = shoppingCartRepository.findAll();
//        return shoppingCart;
//    }

    public ShoppingCart viewCart(ViewCartRequest viewCartRequest) {

//        if (viewCartRequest.getCart() == null ) {
//            throw new ShoppingCartIsEmptyException("Your cart is empty");
//        }
        EcommerceUser foundUser = findByUsername(viewCartRequest.getUsername());

        validateLoginStatusOf(foundUser);

        ShoppingCart shoppingCart = Mapper.mapViewCart(viewCartRequest).getCart();
        shoppingCartRepository.save(shoppingCart);
        return shoppingCart;
    }

    private void validateLoginStatusOf(EcommerceUser ecommerceUser) {
        if (ecommerceUser.isLocked()) throw new InvalidInputEnteredException("invalid username or password");
    }

    @Override
    public UpdateDeliveryDetailsResponse updateDelivery(UpdateDeliveryDetailsRequest updateDeliveryRequest) {
        EcommerceUser ecommerceUser = findByUsername(updateDeliveryRequest.getUsername());
        validateLoginStatusOf(ecommerceUser);

        CustomerInformation customerInformation = Mapper.customerMap(updateDeliveryRequest);
        customerInformationRepository.save(customerInformation);
        UpdateDeliveryDetailsResponse updateDeliveryDetailsResponse = new UpdateDeliveryDetailsResponse();
        updateDeliveryDetailsResponse.setMessage("Delivery details updated successfully");
        return updateDeliveryDetailsResponse;
    }

    @Override
    public UpdateCreditCardInformationResponse updateCard(UpdateCreditCardInformationRequest updateCreditCardInformationRequest) {

        EcommerceUser ecommerceUser = findByUsername(updateCreditCardInformationRequest.getUsername());
        validateLoginStatusOf(ecommerceUser);

        CreditCardInformation creditCardInformation = Mapper.creditCardMap(updateCreditCardInformationRequest);
        creditCardInformationRepository.save(creditCardInformation);

        UpdateCreditCardInformationResponse updateCreditCardInformationResponse = new UpdateCreditCardInformationResponse();
        updateCreditCardInformationResponse.setMessage("Card details updated successfully");
        return updateCreditCardInformationResponse;

    }




    @Override
    public EcommerceUser checkOut(CheckOutRequest checkOutRequest) {
        EcommerceUser foundUser = findByUsername(checkOutRequest.getUsername());
        validateLoginStatusOf(foundUser);
        //Order newOrder = che
        return foundUser;
    }


    @Override
    public List<Order> viewAll(ViewAllUserOrdersRequest viewAllUserOrdersRequest) {
        List<Order> myOrders = orderRepository.findAll();
        return myOrders;
    }


//    @Override
//    public List<Product> searchProduct(String productName) {
//
//        List<Product> productList = productService.getAllProduct();
//        List<Product> search = new ArrayList<>();
//        String selected = productName.trim();
//        for (Product product : productList) {
//            if (product.getProductName().equals(selected)) {
//                search.add(product);
//            }
//        }
//        return search;
//    }

    public List<ViewAllProductResponse> searchProducts(SearchProductRequest searchProductRequest){
        Product foundUser =findProductByName(searchProductRequest.getProductName());
        String productName = searchProductRequest.getProductName();
        List <Product> foundProducts = productService.searchProductByName(productName);
        List<ViewAllProductResponse> search = new ArrayList<>();
        for (Product product : foundProducts){
            ViewAllProductResponse viewAllProductResponse = new ViewAllProductResponse();
            search.add(viewAllProductResponse);
        }
        return search;

    }


    public List<ViewAllProductResponse> viewAllProduct(SearchProductRequest searchProductRequest) {

        Product myProduct = findProductByName(searchProductRequest.getProductName());
        List<Product> viewAllProducts =  productService.viewProducts(myProduct);
        List<ViewAllProductResponse> productResponseList = new ArrayList<>();
        for (Product product : viewAllProducts){
            ViewAllProductResponse viewAllProductResponse = productResponseList.get(0);
            productResponseList.add(viewAllProductResponse);

        }
        return productResponseList;

    }



}
