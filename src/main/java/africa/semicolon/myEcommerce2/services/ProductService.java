package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.dto.request.AddProductRequest;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.response.AddProductResponse;
import africa.semicolon.myEcommerce2.dto.response.CreateProductResponse;
import africa.semicolon.myEcommerce2.dto.response.RemoveProductResponse;

import java.util.List;

public interface ProductService {
    CreateProductResponse create(CreateProductRequest createProduct);
    Product findProductByName(String productName);
   void delete(String productId);
    List<Product>getAllProduct();

    Long count();

    void deleteAll();



    List<Product> searchProductByName(String productName);

    List<Product> viewProducts(Product myProduct);

    AddProductResponse add(AddProductRequest addProductRequest);

    public RemoveProductResponse removeProduct(String productId);
}
