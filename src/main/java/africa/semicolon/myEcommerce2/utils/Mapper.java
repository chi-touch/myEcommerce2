package africa.semicolon.myEcommerce2.utils;

import africa.semicolon.myEcommerce2.data.model.*;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.CheckOutResponse;
import africa.semicolon.myEcommerce2.dto.response.LogOutResponse;
import africa.semicolon.myEcommerce2.dto.response.ViewCartResponse;

import static africa.semicolon.myEcommerce2.data.model.TransactionStatus.PENDING;

public class Mapper {
    public static EcommerceUser mapper(RegisterRequest registerRequest){
        EcommerceUser myUser = new EcommerceUser();
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
        myUser.setReceiverPhoneNumber(registerRequest.getReceiverPhoneNumber());

        CreditCardInformation cardInformation = new CreditCardInformation();
        cardInformation.setCardHolderName(registerRequest.getCardHolderName());
        cardInformation.setCreditCardNumber(registerRequest.getCreditCardNumber());
        cardInformation.setCardExpirationMonth(registerRequest.getCardExpirationMonth());
        cardInformation.setCardExpirationYear(registerRequest.getCardExpirationYear());
        cardInformation.setCvv(registerRequest.getCvv());
        myUser.setCreditCardInfo(cardInformation);


        Address userAddress = Address.builder()
                .country(registerRequest.getCountry())
                .state(registerRequest.getState())
                .street(registerRequest.getStreet())
                .houseNumber(registerRequest.getHouseNumber())
                .build();
        myUser.setAddress(userAddress);
        myUser.setPassword(registerRequest.getPassword());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setRole(registerRequest.getRole());
        myUser.setUsername(registerRequest.getUsername());
        myUser.setLocked(true);

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
        //order.setAmount(orderRequest.getAmount());
//        order.setDeliveryDate(orderRequest.getDeliveryDate());
        Address userAddress = Address.builder()
                .country(orderRequest.getCountry())
                .state(orderRequest.getState())
                .street(orderRequest.getStreet())
                .houseNumber(orderRequest.getHouseNumber())
                .build();
        order.setProductList(orderRequest.getProductList());
       // order.setAmount(orderRequest.getAmount());
//        order.setDeliveryDate(orderRequest.getDeliveryDate());
        return  order;
    }

    public static CheckOutResponse mapCheckoutResponse(Order order) {
        CheckOutResponse checkoutResponse = new CheckOutResponse();
        checkoutResponse.setUsername(order.getBuyer().getUsername());
        checkoutResponse.setOrderId(order.getId());
        return checkoutResponse;
    }


    public static Payment paymentDeliveryMapper(PaymentAtDeliveryRequest paymentAtDeliveryRequest){
        Payment payment = new Payment();
        payment.setAmount(paymentAtDeliveryRequest.getAmount());
        payment.setDeliveryId(paymentAtDeliveryRequest.getDeliveryId());
        payment.setStatus(paymentAtDeliveryRequest.getStatus());
        return payment;
    }

    public static CustomerInformation customerMap(UpdateDeliveryDetailsRequest updateDeliveryDetailsRequest) {
        CustomerInformation customerInformation = new CustomerInformation();
        customerInformation.setReceiverName(updateDeliveryDetailsRequest.getReceiverName());
        customerInformation.setReceiverPhoneNumber(updateDeliveryDetailsRequest.getReceiverPhoneNumber());
        Address deliveryAddress = customerInformation.getDeliveryAddress();
        deliveryAddress.setStreet(updateDeliveryDetailsRequest.getStreet());
        deliveryAddress.setState(updateDeliveryDetailsRequest.getState());
        deliveryAddress.setHouseNumber(updateDeliveryDetailsRequest.getHouseNumber());

        return customerInformation;
    }

    public static CreditCardInformation creditCardMap(UpdateCreditCardInformationRequest updateCreditCardInformationRequest) {
        CreditCardInformation creditCardInformation = new CreditCardInformation();
        creditCardInformation.setCreditCardNumber(updateCreditCardInformationRequest.getCreditCardNumber());
        creditCardInformation.setCvv(updateCreditCardInformationRequest.getCvv());
        creditCardInformation.setCardHolderName(updateCreditCardInformationRequest.getCardHolderName());
        creditCardInformation.setCardExpirationMonth(updateCreditCardInformationRequest.getCardExpirationMonth());
        creditCardInformation.setCardExpirationYear(updateCreditCardInformationRequest.getCardExpirationYear());
        return creditCardInformation;
    }

    public static EcommerceUser mapViewCart(ViewCartRequest viewCartRequest) {
        EcommerceUser ecommerceUser = new EcommerceUser();
        ecommerceUser.setUsername(viewCartRequest.getUsername());
       // ecommerceUser.setCart(viewCartRequest.getCart());
        return ecommerceUser;
    }
}
