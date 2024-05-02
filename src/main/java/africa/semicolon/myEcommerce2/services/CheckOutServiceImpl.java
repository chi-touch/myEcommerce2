package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.repositories.OrderRepository;
import africa.semicolon.myEcommerce2.data.repositories.EcommerceUserRepository;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CheckOutServiceImpl implements CheckOutService{

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    EcommerceUserRepository ecommerceUserRepository;

    @Override
    public Order placeOrder(EcommerceUser ecommerceUser) {
        //if(!ecommerceUser.getUsername().equals(ecommerceUser.getUsername())){
//            throw new InvalidInputEnteredException("this user does not exist");
//        }
//
//        Order myOrder =  ecommerceUserRepository.
        return null;
    }







}
