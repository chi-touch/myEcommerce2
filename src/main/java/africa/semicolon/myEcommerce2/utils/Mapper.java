package africa.semicolon.myEcommerce2.utils;

import africa.semicolon.myEcommerce2.data.model.Address;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;

import static africa.semicolon.myEcommerce2.data.model.Role.CUSTOMER;

public class Mapper {
    public static User mapper(RegisterRequest registerRequest){
        User myUser = new User();
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
        Address userAddress = new Address();
        userAddress.setState(String.valueOf(myUser.getAddress()));
        userAddress.setCountry(String.valueOf(myUser.getAddress()));
        userAddress.setHouseNumber(String.valueOf(myUser.getAddress()));
        userAddress.setStreet(String.valueOf(myUser.getAddress()));
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

//    public static User logMapper(LoginRequest loginRequest){
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//        User foundUser = userRepository.findByUsername(loginRequest.getUsername());
//        foundUser.setLocked(false);
//
//    }
}
