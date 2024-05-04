package africa.semicolon.myEcommerce2.data.model;



import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@Builder
public class Address {
    private String state;
    private String country;
    private String street;
    private String houseNumber;
}
