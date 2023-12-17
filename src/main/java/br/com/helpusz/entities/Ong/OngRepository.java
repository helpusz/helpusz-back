package br.com.helpusz.entities.Ong;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface OngRepository extends MongoRepository<Ong, String> {

  boolean existsByEmail(String email);

  Ong findByEmail(String email);
  
}
