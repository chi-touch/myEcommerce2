package africa.semicolon.myEcommerce2.data.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
@Document
public class Payment {

    @Id
    private String id;
    private BigDecimal balance;
    private String accountNumber;
    private String deliveryId;
    private BigDecimal amount;
    private TransactionStatus status;
    private CreditCardInformation creditCardInfo = new CreditCardInformation();

    private String createTime = createdAt();
    private String createdAt(){
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        return date.format(dateTimeFormatter);
    }



}
