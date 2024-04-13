package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.response.CreateProductResponse;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    @Override
    public CreateProductResponse create(CreateProductRequest createProduct) {

        if (productAlreadyExist(createProduct.getProductName())){
            throw new ProductAlreadyExistException("this product already exist");
        }


        Product product = Mapper.productMapper(createProduct);
        productRepository.save(product);
        CreateProductResponse createProductResponse = new CreateProductResponse();
        createProductResponse.setMessage("product created successfully");
        return createProductResponse;
    }

    private boolean productAlreadyExist(String productName){
        List<Product> productList = productRepository.findAll();
        for (Product product: productList){
            if (product.getProductName().equals(productName)){
                return true;
            }
        }
        return false;
    }

    @Override
    public Product findProductByName(String productName) {

        Product product = productRepository.findByProductName(productName);
        if (product != null) {
            return product;
        } else {
            return null;
        }
    }

    private boolean ifProductExist(String productName){
        return productRepository.searchByProductName(productName) != null;
    }


    @Override
    public void delete(String productName) {
        productRepository.deleteByProductName(productName);
    }




    @Override
    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }



    @Override
    public Long count() {
        return productRepository.count();
    }

    @Override
    public void deleteAll() {
        productRepository.deleteAll();
    }


}
