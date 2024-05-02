package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.CreditCardInformation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CreditCardInformationRepository extends MongoRepository <CreditCardInformation, String> {
}
