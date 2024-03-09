package br.com.helpusz.entities.Volunteer;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.entities.Utils.Email;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {

  boolean existsByEmail(Email email);

  Volunteer findByEmail(Email email);
  
}
