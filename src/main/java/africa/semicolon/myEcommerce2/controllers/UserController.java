package africa.semicolon.myEcommerce2.controllers;


import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.LoginRequest;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.ApiResponse;
import africa.semicolon.myEcommerce2.dto.response.LoginResponse;
import africa.semicolon.myEcommerce2.dto.response.OrderResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import africa.semicolon.myEcommerce2.exceptions.UserNameNotFoundException;
import africa.semicolon.myEcommerce2.services.ProductService;
import africa.semicolon.myEcommerce2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/api")
public class UserController {

         @Autowired
        UserService userService;

        @Autowired
        ProductService productService;

        @PostMapping("/register")
        public ResponseEntity<?> register(@RequestBody RegisterRequest registerRequest) {
            try {
                var answer = userService.register(registerRequest);
                return new ResponseEntity<>(new ApiResponse(true, answer), CREATED);
            }catch (UserAlreadyExistException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @GetMapping("/count")
        public long count() {
            return userService.count();
        }

        @PostMapping("/login")
        public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {

            try {
                var log = userService.login(loginRequest);
                return new ResponseEntity<>(new ApiResponse(true, log), CREATED);
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @GetMapping("/findByUsername/{username}")
        public ResponseEntity<?> findByUsername(@PathVariable String username) {
                try {
                    var find = userService.findByUsername(username);
                    return ResponseEntity.ok(new ApiResponse(true, find));
                } catch (UserNameNotFoundException e) {
                    return new ResponseEntity<>(new ApiResponse(false, e.getMessage()),BAD_REQUEST);
                    //return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ApiResponse(false, e.getMessage()));
                }
        }

        @PostMapping("/order/{userId}")
        public OrderResponse order(@RequestBody OrderRequest orderRequest, @PathVariable String userId) {
            List<Product> productList = productService.getAllProduct();
            return userService.order(orderRequest);
        }

//    @PostMapping("/product")
//    public List<Product> addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }

//        @GetMapping("/product/search")
//        public List<Product> searchProduct(@RequestParam String productName) {
//            return productService.searchProduct(productName);
//        }

        @GetMapping("/users")
        public List<User> getAllUsers() {
            return userService.getAllUsers();
        }

        @DeleteMapping("/users")
        public void deleteAll() {
            userService.deleteAll();
        }
}
