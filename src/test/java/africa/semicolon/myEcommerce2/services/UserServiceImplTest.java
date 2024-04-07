package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Role;

import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginRequest;
import africa.semicolon.myEcommerce2.dto.response.LoginResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static africa.semicolon.myEcommerce2.data.model.Role.ADMIN;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceImplTest {



    RegisterRequest request;
    LoginRequest loginRequest;
    @Autowired
    UserService userService;
    @BeforeEach
    public void setUp(){
//        userService.deleteAll();
        request = new RegisterRequest();
        loginRequest = new LoginRequest();
    }

    @Test

    public void testToRegisterUser(){
        //registerRequest.setAddress(new Address());
        request.setRole(Role.CUSTOMER);
        request.setFirstName("chichi");
        request.setLastName("dave");
        request.setPassword("1234");
        request.setUsername("ami");
        request.setCountry("china");
        request.setState("Lagos");
        request.setHouseNumber("No2 sabo");
        request.setStreet("sabo");

//        userService.register(request);
        RegisterResponse registerResponse =  userService.register(request);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();
        assertEquals(2, userService.count());
    }


    @Test
    public void testToRegisterTwoUser(){

        RegisterRequest request2 = new RegisterRequest();
        request2.setRole(Role.CUSTOMER);
        request2.setFirstName("chichi3");
        request2.setLastName("dave3");
        request2.setPassword("1237");
        request2.setUsername("ami3");
       // userService.register(request2);


        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chich");
        registerRequest1.setLastName("daved");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div");
        //userService.register(registerRequest1);


        RegisterResponse registerResponse1 =  userService.register(registerRequest1);
        RegisterResponse registerResponse2 =  userService.register(request2);

        assertThat(registerResponse1).isNotNull();
        assertThat(registerResponse2).isNotNull();


        assertThat(registerResponse1.getMessage()).isNotNull();
        assertThat(registerResponse2.getMessage()).isNotNull();

        assertEquals(4,userService.count());
    }

    @Test
    @Order(3)
    public void testToLoginUser(){

        loginRequest.setUsername("ami");
        loginRequest.setPassword("1234");
        LoginResponse loginResponse = userService.login(loginRequest);
        assertThat(loginResponse).isNotNull();
        assertThat(loginResponse.getMessage()).isNotNull();
    }

    @Test
    @Order(4)
    public void testToFindByUserName() {
        RegisterRequest requestf = new RegisterRequest();
        requestf.setRole(Role.CUSTOMER);
        requestf.setFirstName("chichiu");
        requestf.setLastName("daveg");
        requestf.setPassword("1239");
        requestf.setUsername("amid");
        requestf.setCountry("chinam");
        requestf.setState("Lagost");
        requestf.setHouseNumber("No2 saboi");
        requestf.setStreet("sabop");

        userService.register(requestf);
//        User expected = new User();
//       expected.setUsername("ami");
//        User actual = userService.findByUsername("ami");
        assertEquals("amid", userService.findByUsername("amid").getUsername());
    }

    @Test
    @Order(5)
    public void testToDeleteAll(){
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername("div2");
        RegisterResponse registerResponse = userService.register(registerRequest1);
        assertThat(registerResponse).isNotNull();
        assertThat(registerResponse.getMessage()).isNotNull();
        userService.deleteAll();
        assertEquals(0,userService.count());
    }

}