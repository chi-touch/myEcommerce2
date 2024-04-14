package africa.semicolon.myEcommerce2.utils;

import africa.semicolon.myEcommerce2.data.model.*;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.dto.request.PaymentAtDeliveryRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;

import static africa.semicolon.myEcommerce2.data.model.TransactionStatus.PENDING;

public class Mapper {
    public static User mapper(RegisterRequest registerRequest){
        User myUser = new User();
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
        Address userAddress = Address.builder()
                .country(registerRequest.getCountry())
                .state(registerRequest.getState())
                .street(registerRequest.getStreet())
                .houseNumber(registerRequest.getHouseNumber())
                .houseNumber(registerRequest.getHouseNumber())
                .build();
        myUser.setPassword(registerRequest.getPassword());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setRole(registerRequest.getRole());
        myUser.setUsername(registerRequest.getUsername());
        myUser.setLocked(false);
        return myUser;
    }

    public static Product productMapper(CreateProductRequest createProduct){
        Product product = new Product();
        product.setProductName(createProduct.getProductName());
        product.setProductType(createProduct.getProductType());
        product.setDescription(createProduct.getDescription());
        product.setPrice(createProduct.getPrice());
        return product;
    }

    public static Order orderMapper(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderStatus(orderRequest.getOrderStatus());
        order.setAmount(orderRequest.getAmount());
        order.setDeliveryDate(orderRequest.getDeliveryDate());
        Address userAddress = Address.builder()
                .country(orderRequest.getCountry())
                .state(orderRequest.getState())
                .street(orderRequest.getStreet())
                .houseNumber(orderRequest.getHouseNumber())
                .houseNumber(orderRequest.getHouseNumber())
                .build();
        order.setAmount(orderRequest.getAmount());
        order.setDeliveryDate(orderRequest.getDeliveryDate());
        return order;
    }

//    public static Payment paymentDeliveryMapper(PaymentAtDeliveryRequest paymentAtDeliveryRequest){
//        Payment payment = new Payment();
//        payment.setDeliveryId(paymentAtDeliveryRequest.getDeliveryId());
//        payment.setAmount(paymentAtDeliveryRequest.getAmount());
//        payment.setAccountNumber(paymentAtDeliveryRequest.getAccountNumber());
//        payment.setAmount(paymentAtDeliveryRequest.getAmount());
//        return payment;
//    }

    public static Payment mapPayment(PaymentAtDeliveryRequest paymentAtDeliveryRequest){
        Payment payment = new Payment();
        payment.setAmount(paymentAtDeliveryRequest.getAmount());
        payment.setDeliveryId(paymentAtDeliveryRequest.getDeliveryId());
        payment.setStatus(PENDING);
        return payment;
    }


}
