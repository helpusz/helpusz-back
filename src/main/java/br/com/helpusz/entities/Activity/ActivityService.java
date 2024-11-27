package br.com.helpusz.entities.Activity;

import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import br.com.helpusz.entities.Ong.Ong;
import br.com.helpusz.entities.User.User;

public interface ActivityService {

	// Ver se ta funcionando essa anotação aqui
  void create(@AuthenticationPrincipal Ong ong, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void update(User user, Activity activity);

	// Se a anotação @AuthenticationPrincipal funcionar muda pro tipo ong ja
	void delete(User user, Activity activity);

  List<Activity> getAll();

}
