package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.Role;
import africa.semicolon.myEcommerce2.data.repositories.EcommerceUserRepository;
import africa.semicolon.myEcommerce2.data.repositories.ProductRepository;
import africa.semicolon.myEcommerce2.dto.request.AddProductRequest;
import africa.semicolon.myEcommerce2.dto.request.CreateProductRequest;
import africa.semicolon.myEcommerce2.dto.response.AddProductResponse;
import africa.semicolon.myEcommerce2.dto.response.CreateProductResponse;
import africa.semicolon.myEcommerce2.dto.response.RemoveProductResponse;
import africa.semicolon.myEcommerce2.exceptions.InvalidInputEnteredException;
import africa.semicolon.myEcommerce2.exceptions.ProductAlreadyExistException;
import africa.semicolon.myEcommerce2.exceptions.ProductNotFoundException;
import africa.semicolon.myEcommerce2.exceptions.UserNameNotFoundException;
import africa.semicolon.myEcommerce2.utils.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class ProductServiceImpl implements ProductService {


    private final ProductRepository productRepository;
    private final EcommerceUserRepository userRepository;
    @Override
    public CreateProductResponse create(CreateProductRequest createProduct) {

        if (productAlreadyExist(createProduct.getProductName())){
            throw new ProductAlreadyExistException("this product already exist");
        }
        EcommerceUser foundUser = userRepository.findById(createProduct.getUserId()).orElseThrow(()-> new UserNameNotFoundException("User not found"));
        if(!foundUser.getRole().equals(Role.ADMIN)){
            throw new InvalidInputEnteredException("You are not allowed to create product");
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

        Product product = productRepository.searchByProductName(productName);
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
    public void delete(String productId) {
        productRepository.deleteByProductName(productId);
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

    @Override
    public List<Product> searchProductByName(String productName) {
        return productRepository.findByProductName(productName);
    }

    @Override
    public List<Product> viewProducts(Product myProduct) {
        return productRepository.findAll();
    }

    @Override
    public AddProductResponse add(AddProductRequest addProductRequest) {
        if (addProductRequest.getUsername() == null){
            throw new InvalidInputEnteredException("Invalid username or password");
        }

        Product product = new Product();
        product.setProductName(addProductRequest.getProductName());
        product.setProductQuantity(addProductRequest.getProductQuantity());
        productRepository.save(product);

        AddProductResponse addProductResponse = new AddProductResponse();
        addProductResponse.setMessage("Product was added successfully");
        return addProductResponse;
    }

    @Override
    public RemoveProductResponse removeProduct(String productId) {
        if (productId == null){
            throw new InvalidInputEnteredException("Product ID cannot be empty");
        }
        boolean foundProduct = productRepository.findById(productId).isPresent();//()-> new ProductNotFoundException("Product"));
        if (!foundProduct){
            throw new InvalidInputEnteredException("Product not found");
        }
        productRepository.deleteById(productId);
        RemoveProductResponse removeResponse = new RemoveProductResponse();
        removeResponse.setMessage("Product was successfully removed");
        return removeResponse;
    }
//        Product foundUser =findProductByName(searchRequest.getProductName());
//        String productName = searchRequest.getProductName();
//        List <Product> foundProducts = .searchProductByName(productName);
//        List<ViewAllProductResponse> search = new ArrayList<>();
//        for (Product product : foundProducts){
//            iewAllProductResponse viewAllProductResponse = new ViewAllProductResponse();
//            search.add(viewAllProductResponse);
//        }
//        return search;
//
//    }




//    public List<Product> searchProductByName(Product myProduct, String searchName) {
//        List<Product> foundProduct = new ArrayList<>();
//        for (Product product : foundProductget()) {
//            if (myProduct.getProductName().contains(searchName)) {
//                foundProduct.add(myProduct);
//            }
//        }
//        return foundProduct;
//    }

//    public List<Contact> searchContactsByName(User foundUser, String searchName) {
//        List<Contact> foundContact = new ArrayList<>();
//        for (Contact contact : foundUser.getContacts()) {
//            if (contact.getName().contains(searchName)) {
//                foundContact.add(contact);
//            }
//        }
//        return foundContact;
//    }


}
