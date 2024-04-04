package africa.semicolon.myEcommerce2.utils;

import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.data.model.user.Address;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;

import static africa.semicolon.myEcommerce2.data.model.Role.CUSTOMER;

public class Mapper {
    public static User mapper(RegisterRequest registerRequest){
        User myUser = new User();
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
        Address userAddress = myUser.getAddress();
        myUser.setPassword(registerRequest.getPassword());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setRole(registerRequest.getRole());
        myUser.setUsername(registerRequest.getUsername());
        myUser.setLocked(false);
        return myUser;
    }

//    public static User logMapper(LoginRequest loginRequest){
//        String username = loginRequest.getUsername();
//        String password = loginRequest.getPassword();
//        User foundUser = userRepository.findByUsername(loginRequest.getUsername());
//        foundUser.setLocked(false);
//
//    }
}
