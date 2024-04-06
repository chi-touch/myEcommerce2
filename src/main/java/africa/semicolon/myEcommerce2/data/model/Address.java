package africa.semicolon.myEcommerce2.data.model;



import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
public class Address {

    private String state;
    private String country;
    private String street;
    private String houseNumber;
}
