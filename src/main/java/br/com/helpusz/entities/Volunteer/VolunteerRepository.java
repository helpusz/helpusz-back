package br.com.helpusz.entities.Volunteer;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface VolunteerRepository extends MongoRepository<Volunteer, String> {

  boolean existsByEmail(String email);

  Volunteer findByEmail(String email);
  
}
