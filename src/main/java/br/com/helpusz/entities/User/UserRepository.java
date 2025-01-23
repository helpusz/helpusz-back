package br.com.helpusz.entities.User;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.entities.Ong.OngCategoryEnum;

public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByEmail(Email email);

  Optional<User> findByEmail(Email email);

	List<User> findAllByTypeAccount(TypeAccountEnum typeAccount);

	List<User> findAllByCategory(OngCategoryEnum category);

  boolean existsByCategory(OngCategoryEnum category);

  Optional<User> findById(Long id);

}
