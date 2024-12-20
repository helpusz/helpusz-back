package br.com.helpusz.entities.Activity;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.helpusz.entities.Ong.OngCategoryEnum;

public interface ActivityRepository extends MongoRepository<Activity, String> {

  List<Activity> findAllByVolunteersContains(String volunteerId);

	List<Activity> findAllByOngCategory(OngCategoryEnum category);

}
