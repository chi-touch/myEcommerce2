package africa.semicolon.myEcommerce2.data.model;



import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Data
@Document
public class EcommerceUser {
    @Id
    private String userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    private String receiverPhoneNumber;


    private CreditCardInformation creditCardInfo;
    //private List<Product> productList = new ArrayList<>();
    private Role role;
    @Indexed(unique = true)
    private String username;
    private boolean isLocked = false;




    //@DBRef
    //private Payment payment;

//    @DBRef
    private Address address;

    private String createTime = createdAt();

    private String createdAt(){
        LocalDateTime date = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return date.format(dateTimeFormatter);
    }

    private ShoppingCart cart;

    //private boolean logOut;

}
