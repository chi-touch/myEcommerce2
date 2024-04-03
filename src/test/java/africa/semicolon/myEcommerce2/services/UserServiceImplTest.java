package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Role;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {



    RegisterRequest  registerRequest;
    LoginRequest loginRequest;
    @Autowired
    UserService userService;
    @BeforeEach
    public void setUp(){
        userService.deleteAll();
        registerRequest = new RegisterRequest();
        loginRequest = new LoginRequest();
    }

    @Test
    public void testToRegisterUser(){
        //registerRequest.setAddress(new Address());
        registerRequest.setRole(Role.CUSTOMER);
        registerRequest.setFirstName("chichi");
        registerRequest.setLastName("dave");
        registerRequest.setPassword("1234");
        registerRequest.setUsername("ami");
        userService.register(registerRequest);
        assertEquals(1, userService.count());
    }
    @Test
    public void testToRegisterTwoUser(){
        registerRequest.setRole(Role.CUSTOMER);
        registerRequest.setFirstName("chichi");
        registerRequest.setLastName("dave");
        registerRequest.setPassword("1234");
        registerRequest.setUsername("ami");
        userService.register(registerRequest);

        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(Role.ADMIN);
        registerRequest1.setFirstName("chich");
        registerRequest1.setLastName("daved");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div");
        userService.register(registerRequest1);

        assertEquals(2,userService.count());
    }

    @Test
    public void testToLoginUser(){
        registerRequest.setRole(Role.CUSTOMER);
        registerRequest.setFirstName("chichi");
        registerRequest.setLastName("dave");
        registerRequest.setPassword("1234");
        registerRequest.setUsername("ami");
        userService.register(registerRequest);

        loginRequest.setUsername("ami");
        loginRequest.setPassword("1234");
        userService.login(loginRequest);
        User user = userService.getByUsername("ami");
        assertFalse(user.isLocked());
    }

}