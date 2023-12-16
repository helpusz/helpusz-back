package br.com.helpusz.helpusz.entities.user;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {

  
  
}
