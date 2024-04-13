package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.math.BigDecimal;

public interface PaymentRepository  extends MongoRepository <Payment, String> {

    int paidAmount(BigDecimal amount);
}
