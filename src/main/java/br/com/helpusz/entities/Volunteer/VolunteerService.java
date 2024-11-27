package br.com.helpusz.entities.Volunteer;

import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.User;

@Service
public interface VolunteerService {

	void update(User user);

}
