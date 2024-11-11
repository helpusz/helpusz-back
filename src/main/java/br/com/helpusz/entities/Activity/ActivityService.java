package br.com.helpusz.entities.Activity;

import org.springframework.security.core.annotation.AuthenticationPrincipal;

import br.com.helpusz.entities.Ong.Ong;

public interface ActivityService {

  void create(@AuthenticationPrincipal Ong ong, Activity activity);

	void update(Activity activity);

	void delete(Activity activity);

}
