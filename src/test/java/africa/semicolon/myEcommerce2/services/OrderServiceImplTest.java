package africa.semicolon.myEcommerce2.services;

import africa.semicolon.myEcommerce2.data.model.Order;
import africa.semicolon.myEcommerce2.data.model.Product;
import africa.semicolon.myEcommerce2.data.model.User;
import africa.semicolon.myEcommerce2.dto.request.OrderRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class OrderServiceImplTest {

    @Autowired
    OrderService orderService;

    @Autowired
    UserService userService;

    @Test
    public void testThatOrderCanBeCreated(){
        List<User> userList = userService.getAllUsers();
        User user = userList.get(0);
        OrderRequest orderRequest = new OrderRequest();

        List<Product> productList = new ArrayList<>(List.of(new Product()));
        orderRequest.setAmount(BigDecimal.valueOf(2000));
        orderRequest.setCountry("Ghana");
        orderRequest.setState("Abia");
        orderRequest.setStreet("Sabo");
        orderRequest.setHouseNumber("12");
        Order order = orderService.order(orderRequest, user.getId(), productList);
        assertThat(order).isNotNull();
    }

}