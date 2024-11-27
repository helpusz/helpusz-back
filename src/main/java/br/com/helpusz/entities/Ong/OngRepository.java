package br.com.helpusz.entities.Ong;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.entities.Utils.Email;

public interface OngRepository extends MongoRepository<Ong, String> {

  boolean existsByEmail(Email email);

  Optional <Ong> findByEmail(Email email);

}
