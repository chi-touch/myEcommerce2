package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;

import java.util.List;

public interface OrderService {

    Order order(OrderRequest orderRequest, String userId, List<Product> productList);

}
