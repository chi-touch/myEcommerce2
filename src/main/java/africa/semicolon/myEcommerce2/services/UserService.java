package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.*;

import java.util.List;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);

    long count();
    LoginResponse login(LoginRequest loginRequest);

    LogOutResponse logOut(LogOutRequest logOutRequest);

    AddItemResponse addItem(AddProductRequest addProductRequest);
    AddProductResponse addProduct(AddProductRequest addProductRequest);

    RemoveItemResponse removeItem(RemoveItemResponse removeItemResponse);

    RemoveProductResponse removeProduct(RemoveProductRequest removeProductRequest);

    ViewCartResponse viewCart(ViewCartRequest viewCartRequest);

    UpdateDeliveryDetailsResponse updateDelivery(UpdateDeliveryDetailsRequest updateDeliveryRequest);

    UpdateCreditCardInformationResponse updateCard(UpdateCreditCardInformationResponse updateCreditCardInformationResponse);

    CheckOutResponse checkOut(CheckOutRequest checkOutRequest);

    ViewOrderResponse viewOrder(ViewOrderResponse viewOrderResponse);

    ViewAllOrderResponse viewAll(ViewAllUserOrdersRequest viewAllUserOrdersRequest);

    EcommerceUser findByUsername(String username);

    OrderResponse order(OrderRequest orderRequest);

     List<ViewAllProductResponse> searchProducts(SearchProductRequest searchProductRequest);

    List<EcommerceUser> getAllUsers();

    List<Product> searchProductByName(String productName);

    void deleteAll();

    CreateProductResponse create(CreateProductRequest createProduct);
    Product findProductByName(String productName);

    List<Product>getAllProduct();

    TransferResponse transfer(TransferRequest transferRequest);

    PaymentDeliveryResponse delivery(PaymentAtDeliveryRequest paymentAtDeliveryRequest);
}
