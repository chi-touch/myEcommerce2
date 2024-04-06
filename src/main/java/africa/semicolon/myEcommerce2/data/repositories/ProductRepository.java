package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.Product;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> searchByProductName(String productName);
    Product deleteByProductName(String productName);

    Product findByProductName(String productName);


    //    Product findByProductName(String productName);

//    String delete(String productName);

//    boolean ifProductExist(String productName);
}
