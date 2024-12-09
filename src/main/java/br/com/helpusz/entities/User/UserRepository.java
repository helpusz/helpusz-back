package br.com.helpusz.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.entities.Utils.Email;

public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByEmail(Email email);

  Optional<User> findByEmail(Email email);

	List<User> findAllByTypeAccount(TypeAccountEnum typeAccount);

}
