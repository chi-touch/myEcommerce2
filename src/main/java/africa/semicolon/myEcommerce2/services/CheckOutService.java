package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.data.model.Order;

public interface CheckOutService {
        Order placeOrder(EcommerceUser ecommerceUser);

}
