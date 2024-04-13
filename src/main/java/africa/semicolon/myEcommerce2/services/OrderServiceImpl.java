package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Address;
import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.OrderStatus;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.repositories.OrderRepository;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

import static africa.semicolon.myEcommerce2.data.model.OrderStatus.PENDING;
import static africa.semicolon.myEcommerce2.data.model.OrderStatus.SUCCESS;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService{

    private final OrderRepository orderRepository;
    @Override
    public Order order(OrderRequest orderRequest, String userId, List<Product> productList) {
        String totalAmount = String.valueOf(orderRequest.getAmount());
        Address address = Address.builder()
                .state(orderRequest.getState())
                .country(orderRequest.getCountry())
                .street(orderRequest.getStreet())
                .houseNumber(orderRequest.getHouseNumber())
                .build();

        Order order = new Order();
        order.setProductList(productList);
        order.setAmount(new BigDecimal(totalAmount));
        order.setAddress(address);
        order.setOrderStatus(SUCCESS);
        order.setId(userId);

        Order savedOrder = orderRepository.save(order);
        return savedOrder;
    }
}
