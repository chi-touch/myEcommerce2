package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.EcommerceUser;

import africa.semicolon.myEcommerce2.data.model.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EcommerceUserRepository extends MongoRepository<EcommerceUser, String> {
   EcommerceUser findByUsername(String username);

   EcommerceUser findByRole(Role role);


   String getByUsername(String username);
}
