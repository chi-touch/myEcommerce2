package africa.semicolon.myEcommerce2.data.model.user;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Address {
    @Id
    private Long id;
    private String houseNumber;
    private String state;
    private String country;
    private String street;
}
