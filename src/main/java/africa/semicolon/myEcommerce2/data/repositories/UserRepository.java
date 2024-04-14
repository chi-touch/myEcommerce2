package africa.semicolon.myEcommerce2.data.repositories;

import africa.semicolon.myEcommerce2.data.model.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {
   User findByUsername(String username);


   String getByUsername(String username);
}
