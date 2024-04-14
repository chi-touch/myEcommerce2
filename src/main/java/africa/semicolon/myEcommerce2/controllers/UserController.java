package africa.semicolon.myEcommerce2.controllers;


import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.dto.request.LoginRequest;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.request.SearchProductRequest;
import africa.semicolon.myEcommerce2.dto.response.ApiResponse;
import africa.semicolon.myEcommerce2.dto.response.OrderResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.ProductDoesExistException;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import africa.semicolon.myEcommerce2.exceptions.UserNameNotFoundException;
import africa.semicolon.myEcommerce2.services.ProductService;
import africa.semicolon.myEcommerce2.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

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

        @PostMapping("/order")
        public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest) {
            try {
                List<Product> productList = productService.getAllProduct();
                return new ResponseEntity<>(new ApiResponse(true,productList),CREATED);
            }catch (ProductDoesExistException e) {
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }



//    @PostMapping("/product")
//    public List<Product> addProduct(@RequestBody Product product) {
//        return productService.addProduct(product);
//    }

        @PatchMapping("/searchProduct")
        public ResponseEntity<?> searchProduct(@RequestBody SearchProductRequest searchProductRequest) {
            try{
                var result = userService.searchProducts(searchProductRequest);
                return new ResponseEntity<>(new ApiResponse(true, result), OK);
            }catch(ProductDoesExistException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()) ,BAD_REQUEST);
            }

        }

        @GetMapping("/users")
        public List<EcommerceUser> getAllUsers() {
            return userService.getAllUsers();
        }

        @DeleteMapping("/users")
        public void deleteAll() {
            userService.deleteAll();
        }
}
