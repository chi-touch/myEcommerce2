package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.Product;

import africa.semicolon.myEcommerce2.dto.request.AddProductRequest;
import africa.semicolon.myEcommerce2.dto.response.AddProductResponse;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    Product searchByProductName(String productName);
    Product deleteByProductName(String productName);

   List<Product> findByProductName(String productName);

    Product addMyProduct(AddProductRequest addProductRequest);


    //    Product findByProductName(String productName);

//    String delete(String productName);

//    boolean ifProductExist(String productName);
}
