package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.request.RegisterRequest;
import africa.semicolon.myEcommerce2.dto.response.CreateProductResponse;
import africa.semicolon.myEcommerce2.dto.response.RegisterResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static africa.semicolon.myEcommerce2.data.model.ProductType.*;
import static africa.semicolon.myEcommerce2.data.model.ProductType.UTENSILS;
import static africa.semicolon.myEcommerce2.data.model.Role.ADMIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;

    @Autowired
    UserService userService;
    @BeforeEach
    public void setUp(){
        productService.deleteAll();
    }


    @Test
    public void testToCreateProduct(){
        RegisterResponse registerResponse = registerRequest("Chi");

        CreateProductRequest createProductRequest = getCreateProductRequest(registerResponse.getUserId(),"fan");


        CreateProductResponse createProductResponse = productService.create(createProductRequest);
        productService.count().equals(1L);
        assertThat(createProductResponse.getMessage()).isNotNull();
    }

    private RegisterResponse registerRequest(String username) {
        RegisterRequest registerRequest1 = new RegisterRequest();
        registerRequest1.setRole(ADMIN);
        registerRequest1.setFirstName("chichi2");
        registerRequest1.setLastName("dave2");
        registerRequest1.setPassword("1235");
        registerRequest1.setUsername(username);
        return userService.register(registerRequest1);
    }

    private static CreateProductRequest getCreateProductRequest(String userId,String productName) {
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName(productName);
        createProductRequest.setProductType(ELECTRONICS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        createProductRequest.setUserId(userId);
        return createProductRequest;
    }

    @Test
    public void testToCreateTwoProducts(){

        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("spoon");
        createProductRequest.setProductType(ELECTRONICS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        productService.create(createProductRequest);

        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setDescription("phones");
        createProductRequest1.setProductName("samsung");
        createProductRequest1.setPrice(BigDecimal.valueOf(2000));
        createProductRequest1.setProductType(ACCESSORIES);

        CreateProductResponse createProductResponse = productService.create(createProductRequest1);
        productService.count().equals(2L);
        assertThat(createProductResponse.getMessage()).isNotNull();
    }

    @Test
    public void testToCreateMoreProducts(){
        CreateProductRequest createProductRequest2 = new CreateProductRequest();
        createProductRequest2.setDescription("phones");
        createProductRequest2.setProductName("techno");
        createProductRequest2.setPrice(BigDecimal.valueOf(2000));
        createProductRequest2.setProductType(ACCESSORIES);
         createProductRequest2.setUserId("user");
        productService.create(createProductRequest2);

        CreateProductRequest createProductRequest3 = new CreateProductRequest();
        createProductRequest3.setDescription("phones");
        createProductRequest3.setProductName("bag");
        createProductRequest3.setPrice(BigDecimal.valueOf(2000));
        createProductRequest3.setProductType(FASHION);
        productService.create(createProductRequest3);

        CreateProductRequest createProductRequest4 = new CreateProductRequest();
        createProductRequest4.setDescription("phones");
        createProductRequest4.setProductName("television");
        createProductRequest4.setPrice(BigDecimal.valueOf(2000));
        createProductRequest4.setProductType(ELECTRONICS);

        CreateProductResponse createProductResponse = productService.create(createProductRequest4);
        productService.count().equals(5L);
        assertThat(createProductResponse.getMessage()).isNotNull();

    }

//    @Test
//    public void testToCreatExistingProduct(){
//        CreateProductRequest createProductRequest =new CreateProductRequest();
//        createProductRequest.setProductName("knife");
//        createProductRequest.setProductType(UTENSILS);
//        createProductRequest.setPrice(BigDecimal.valueOf(1000));
//        createProductRequest.setDescription("kitchen tools");
//        assertThrows(ProductAlreadyExistException.class, ()-> productService.create(createProductRequest));
//    }
//
    @Test
    public void testToDelete(){
        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setProductName("knife");
        createProductRequest1.setProductType(UTENSILS);
        createProductRequest1.setPrice(BigDecimal.valueOf(1000));
        createProductRequest1.setDescription("kitchen tools");
        productService.create(createProductRequest1);
        productService.delete("knife");
        assertEquals(0, productService.getAllProduct().size());
    }

    @Test
    public void testToFindAllProduct(){
        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setProductName("knife");
        createProductRequest1.setProductType(UTENSILS);
        createProductRequest1.setPrice(BigDecimal.valueOf(1000));
        createProductRequest1.setDescription("kitchen tools");
        productService.create(createProductRequest1);
        productService.getAllProduct();
        assertEquals(1,productService.count());
    }

//    @Test
//    public void testToFindById(){
//        Product result = productService.findById("660fdf304fcf2553f2633c63");
//        assertNotNull(result);
//    }

    @Test
    public void testToFindByProductName(){
        CreateProductRequest createProductRequest = new CreateProductRequest();
        createProductRequest.setProductName("knife");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        productService.create(createProductRequest);
        assertEquals("knife", productService.findProductByName("knife").getProductName());
    }



}