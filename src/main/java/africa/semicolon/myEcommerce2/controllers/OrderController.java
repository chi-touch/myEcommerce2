package africa.semicolon.myEcommerce2.controllers;

import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import africa.semicolon.myEcommerce2.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
        @Autowired
        OrderService orderService;
        @PostMapping("/create")
        public Order order(@RequestBody OrderRequest orderRequest, @RequestParam String userId, @RequestBody List<Product> productList) {
            return orderService.order(orderRequest, userId, productList);
        }
}
