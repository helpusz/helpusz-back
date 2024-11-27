package br.com.helpusz.entities.Volunteer;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.entities.Utils.Email;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {

  boolean existsByEmail(Email email);

  Optional <Volunteer> findByEmail(Email email);

}
