package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;

import java.util.List;

public interface ProductService {
    Product create(CreateProductRequest createProduct);
    List<Product> findProductByName(String productName);
    Product findById(Long id);
    void delete(Product productName);

    List<Product>getAllProduct();


    Long getNumberOfProduct();

    Long count();
}
