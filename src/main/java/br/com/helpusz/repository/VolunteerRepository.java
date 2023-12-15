package br.com.helpusz.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.model.Volunteer;


public interface VolunteerRepository extends MongoRepository<Volunteer, String> {
  Volunteer findByEmail(String email);
}
