package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<EcommerceUser, String> {
   EcommerceUser findByUsername(String username);


   String getByUsername(String username);
}
