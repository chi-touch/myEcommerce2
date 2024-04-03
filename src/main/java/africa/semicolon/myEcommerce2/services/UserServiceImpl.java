package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.repositories.UserRepository;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class UserServiceImpl implements UserService{
   @Autowired
    private UserRepository userRepository;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        User myUser = new User();
        myUser.setFirstName(registerRequest.getFirstName());
        myUser.setLastName(registerRequest.getLastName());
       // myUser.setAddress(registerRequest.getAddress());
        myUser.setPassword(registerRequest.getPassword());
        myUser.setEmail(registerRequest.getEmail());
        myUser.setRole(registerRequest.getRole());
        myUser.setUsername(registerRequest.getUsername());
        myUser.setLocked(false);
        userRepository.save(myUser);

        RegisterResponse registerResponse = new RegisterResponse();
        registerResponse.setMessage("Registration is successful");

        return registerResponse;
    }

    @Override
    public long count() {
        return userRepository.count();
    }


    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();
        User foundUser = userRepository.findByUsername(loginRequest.getUsername());

        foundUser.setLocked(false);
        userRepository.save(foundUser);

        if (isValidUsernameAndPassword(username, password)) {
            LoginResponse response = new LoginResponse();
            response.setMessage("Login successful");
            return response;
        } else {

            throw new InvalidInputEnteredException("Invalid username or password");
        }

    }

    @Override
    public User getByUsername(String username) {
        return userRepository.getByUsername(username);
    }

    @Override
    public void deleteAll() {
        userRepository.deleteAll();
    }

    private boolean isValidUsernameAndPassword(String username, String password) {
        List<User> userList = userRepository.findAll();
        for (User user : userList) {
            if (user.getUsername().equals(username) && user.getPassword().equals(password)) {
                return true;
            }
        }
        return false;
    }
}
