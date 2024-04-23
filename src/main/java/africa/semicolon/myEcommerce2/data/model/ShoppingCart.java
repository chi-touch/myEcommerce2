package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ShoppingCart {
    private List<Item> items = new ArrayList<>();
}
