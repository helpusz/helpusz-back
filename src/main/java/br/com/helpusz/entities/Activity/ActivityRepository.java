package br.com.helpusz.entities.Activity;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ActivityRepository extends MongoRepository<Activity, String> {

  List<Activity> findAllByVolunteersContains(String volunteerId);

}
