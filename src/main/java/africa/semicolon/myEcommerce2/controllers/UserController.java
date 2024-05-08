package africa.semicolon.myEcommerce2.controllers;


import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.dto.request.*;
import africa.semicolon.myEcommerce2.dto.response.ApiResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.ProductDoesExistException;
import africa.semicolon.myEcommerce2.exceptions.UserAlreadyExistException;
import africa.semicolon.myEcommerce2.services.ProductService;
import africa.semicolon.myEcommerce2.services.UserService;
import lombok.var;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api")
public class UserController {
        private final UserService userService;

        private final ProductService productService;

    public UserController(UserService userService, ProductService productService) {
        this.userService = userService;
        this.productService = productService;
    }

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
        @PostMapping("/logout")
        public ResponseEntity<?> logOut(@RequestBody LogOutRequest logOutRequest) {
            try {
                var log = userService.logOut(logOutRequest);
                return new ResponseEntity<>(new ApiResponse(true, log), CREATED);
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @PostMapping("/product/create")
        public ResponseEntity<?> createProduct(@RequestBody CreateProductRequest createProductRequest) {
            try {
                var answer = productService.create(createProductRequest);
                return new ResponseEntity<>(new ApiResponse(true, answer), CREATED);
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @DeleteMapping("/removeProduct/{productId}")
        public ResponseEntity<?> removeProduct(@PathVariable String productId) {
            try {
                var answer = productService.removeProduct(productId);
                return new ResponseEntity<>(new ApiResponse(true, answer), CREATED);
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

        @PostMapping("/viewCart")
        public ResponseEntity<?> viewCart(@RequestBody ViewCartRequest viewCartRequest) {
            try {
                var answer = userService.viewCart(viewCartRequest);
                return new ResponseEntity<>(new ApiResponse(true, answer), CREATED);
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false, e.getMessage()), BAD_REQUEST);
            }
        }

    @PostMapping("/addProduct")
    public ResponseEntity<?> addProduct(@RequestBody AddProductRequest addProductRequest) {
        try {
            var answer = userService.addProduct(addProductRequest);
            return new ResponseEntity<>(new ApiResponse(true, answer), CREATED);
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

        @GetMapping("/findByUsername/{username}")
        public ResponseEntity<?> findByUsername(@PathVariable String username){
            try {
                var result = userService.findByUsername(username);
                return ResponseEntity.ok(new ApiResponse(true, result));
            }catch (InvalidInputEnteredException e){
                return new ResponseEntity<>(new ApiResponse(false,e.getMessage()), BAD_REQUEST);
            }
        }

}
