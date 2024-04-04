package africa.semicolon.myEcommerce2.data.model;

import africa.semicolon.myEcommerce2.data.model.user.Address;
import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany
    private List<Product> productList = new ArrayList<>();
    private Role role;
    private String username;
    private boolean isLocked;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Address address;
}
