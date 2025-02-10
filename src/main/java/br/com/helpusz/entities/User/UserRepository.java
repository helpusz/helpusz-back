package br.com.helpusz.entities.User;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.entities.Ong.OngCategoryEnum;

public interface UserRepository extends MongoRepository<User, String> {

	boolean existsByEmail(Email email);

  Optional<User> findByEmail(Email email);

	List<User> findAllByTypeAccount(TypeAccountEnum typeAccount);

	List<User> findAllByCategory(OngCategoryEnum category);

  boolean existsByCategory(OngCategoryEnum category);

  Optional<User> findById(Long id);

	@Query("{ '_id': { $in: ?0 } }")
	List<User> findAllById(List<ObjectId> ids);


}
