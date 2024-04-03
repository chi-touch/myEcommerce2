package africa.semicolon.myEcommerce2.data.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private Role role;
    private String username;
    private boolean isLocked;
//    @OneToOne
//    private Address address;
}
