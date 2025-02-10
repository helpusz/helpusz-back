package br.com.helpusz.entities.Activity;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.User.User;

public interface ActivityService {

  void create(String ongId, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void update(User user, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void delete(User user, Activity activity);

  List<Activity> getAll();

	List<Activity> getAllByVolunteerId(User user);

	List<Activity> getAllByOngCategory(OngCategoryEnum category);

	void enterIntoActivity(String userId, String activityId);

  List<Activity> getActivitiesRegisteredByVolunteerId(String userId);

  List<Activity> getAllByOngId(String ongId);

	String uploadActivityImage(String id, MultipartFile file) throws Exception;

}
