package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import africa.semicolon.myEcommerce2.exceptions.ProductDoesExistException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Override
    public Product create(CreateProductRequest createProduct) {
        if (!productAlreadyExist(createProduct.getProductName())){
            throw new ProductDoesExistException("this product does not exist");
        }
        Product product = Mapper.productMapper(createProduct);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    @Override
    public List<Product> findProductByName(String productName) {
        return productRepository.searchByProductName(productName);
    }

    @Override
    public Product findById(String id) {
        Optional<Product> product = productRepository.findById(id);

        if (product.isEmpty()){
            throw new RuntimeException("not found");

        }
      return product.get();
    }

    @Override
    public void delete(String productName) {
        productRepository.deleteByProductName(productName);
    }

    private boolean productAlreadyExist(String productName){
        //return productRepository.findByProductName(productName)!= null;
        return true;
    }


    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }



    @Override
    public Long count() {
        return productRepository.count();
    }




}
