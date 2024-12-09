package br.com.helpusz.entities.Activity;

import java.util.List;

import br.com.helpusz.entities.User.User;

public interface ActivityService {

  void create(String ongId, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void update(User user, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void delete(User user, Activity activity);

  List<Activity> getAll();

	List<Activity> getAllByVolunteerId(User user);

	void enterIntoActivity(String userId, String activityId);

}
