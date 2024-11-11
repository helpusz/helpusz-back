package br.com.helpusz.entities.Activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.Ong.Ong;

@Service
public class ActivityServiceImpl  implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Override
	public void create(@AuthenticationPrincipal Ong ong, Activity activity) {
		activity.setOngId(ong.getId());

		this.activityRepository.save(activity);
	}

	@Override
	public void update(Activity activity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'update'");
	}

	@Override
	public void delete(Activity activity) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'delete'");
	}



}
