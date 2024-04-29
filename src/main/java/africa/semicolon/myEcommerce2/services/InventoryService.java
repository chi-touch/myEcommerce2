package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Item;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.dto.request.AddProductRequest;
import africa.semicolon.myEcommerce2.dto.request.FindProductRequest;
import africa.semicolon.myEcommerce2.dto.request.RemoveProductRequest;
import africa.semicolon.myEcommerce2.dto.response.AddProductResponse;
import africa.semicolon.myEcommerce2.dto.response.FindAllProductsResponse;
import africa.semicolon.myEcommerce2.dto.response.FindProductResponse;
import africa.semicolon.myEcommerce2.dto.response.RemoveProductResponse;

import java.util.List;

public interface InventoryService {
    Product findBy(String id);
    AddProductResponse addProductWith(AddProductRequest addProductRequest);
    RemoveProductResponse removeProductWith(RemoveProductRequest removeProductRequest);
    FindProductResponse findProductWith(FindProductRequest findProductRequest);
    FindAllProductsResponse findAllProducts();
    void validate(int quantityOfProduct, Product product);
    void validate(List<Item> items);
    void updateProductQuantity(List<Item> items);
}
