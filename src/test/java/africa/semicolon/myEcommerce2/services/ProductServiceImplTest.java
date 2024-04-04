package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static africa.semicolon.myEcommerce2.data.model.ProductType.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ProductServiceImplTest {

    @Autowired
    ProductService productService;
    CreateProductRequest createProductRequest;
    @BeforeEach
    public void setUp(){

        createProductRequest = new CreateProductRequest();
    }

    @Test
    public void testToCreateProduct(){
        createProductRequest.setProductName("knife");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        productService.create(createProductRequest);
        assertThat(productService.count(), is(1L));
    }

    @Test
    public void testToCreateTwoProducts(){

        CreateProductRequest createProductRequest1 = new CreateProductRequest();
        createProductRequest1.setDescription("phones");
        createProductRequest1.setProductName("samsung");
        createProductRequest1.setPrice(BigDecimal.valueOf(2000));
        createProductRequest1.setProductType(ACCESSORIES);
        productService.create(createProductRequest1);
       assertThat(productService.count(),is(2L));
    }

    @Test
    public void testToCreateMoreProducts(){

        CreateProductRequest createProductRequest2 = new CreateProductRequest();
        createProductRequest2.setDescription("phones");
        createProductRequest2.setProductName("techno");
        createProductRequest2.setPrice(BigDecimal.valueOf(2000));
        createProductRequest2.setProductType(ACCESSORIES);
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
        productService.create(createProductRequest4);
        assertThat(productService.count(),is(4L));
    }

    @Test
    public void testToCreatExistingProduct(){
        createProductRequest.setProductName("knife");
        createProductRequest.setProductType(UTENSILS);
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        assertThrows(ProductAlreadyExistException.class,()-> productService.create(createProductRequest));
    }

    @Test
    public void testToDelete(){
        productService.delete();
    }



}