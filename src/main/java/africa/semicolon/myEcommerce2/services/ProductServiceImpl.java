package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ProductType;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    @Override
    public Product create(CreateProductRequest createProduct) {
        if (productAlreadyExist(createProduct.getProductName())){
            throw new ProductAlreadyExistException("this product already exist");
        }
        Product product = Mapper.productMapper(createProduct);
        Product savedProduct = productRepository.save(product);
        return savedProduct;
    }

    private boolean productAlreadyExist(String productName){
        return productRepository.findByProductName(productName)!= null;    }

    @Override
    public List<Product> findProductByName(String productName) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void delete(Product productName) {
        productRepository.delete(productName);

    }

    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    @Override
    public Long getNumberOfProduct() {
        return productRepository.count();
    }

    @Override
    public Long count() {
        return productRepository.count();
    }


}
