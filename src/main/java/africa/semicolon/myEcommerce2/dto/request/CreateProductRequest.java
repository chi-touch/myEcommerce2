package africa.semicolon.myEcommerce2.dto.request;

import africa.semicolon.myEcommerce2.data.model.ProductType;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
@Setter
@Getter

public class CreateProductRequest {
    private String productName;
    private ProductType productType;
    private BigDecimal price;
    private String description;

    @Override
    public String toString() {
        return "CreateProductRequest{" +
                "productName='" + productName + '\'' +
                ", productType=" + productType +
                ", price=" + price +
                ", description='" + description + '\'' +
                '}';
    }
}
