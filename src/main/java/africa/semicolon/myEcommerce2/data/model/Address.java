package africa.semicolon.myEcommerce2.data.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity(name = "UserAddress")
public class Address {
    @Id
    private Long id;
    private String state;
    private String country;
    private String street;
    private String houseNumber;
}
