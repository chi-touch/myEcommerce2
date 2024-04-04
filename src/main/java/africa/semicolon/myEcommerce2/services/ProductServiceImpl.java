package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.ProductType;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
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
        Product product = new Product();
        product.setProductName(createProduct.getProductName());
        product.setProductType(ProductType.valueOf(createProduct.getProductType().toUpperCase()));
        product.setPrice(createProduct.getPrice());
        Product savedProduct = productRepository.save(product);

        return savedProduct;
    }

    @Override
    public List<Product> findProductByName(String productName) {
        return null;
    }

    @Override
    public Product findById(Long id) {
        return null;
    }

    @Override
    public void delete(String productName) {

    }

    @Override
    public List<Product> getAllProduct() {
        return null;
    }

    @Override
    public Long getNumberOfProduct() {
        return productRepository.count();
    }


}
