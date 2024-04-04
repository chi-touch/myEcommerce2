package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static africa.semicolon.myEcommerce2.data.model.ProductType.UTENSILS;
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
        createProductRequest.setProductType("UTENSILS");
        createProductRequest.setPrice(BigDecimal.valueOf(1000));
        createProductRequest.setDescription("kitchen tools");
        productService.create(createProductRequest);
        assertEquals(1,productService.getNumberOfProduct());


    }

}