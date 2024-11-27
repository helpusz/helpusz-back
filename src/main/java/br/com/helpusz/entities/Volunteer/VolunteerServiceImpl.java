package br.com.helpusz.entities.Volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.User;
import br.com.helpusz.exception.HelpuszException;

@Service
public class VolunteerServiceImpl implements VolunteerService {

	@Autowired
	private VolunteerRepository volunteerRepository;

	public void update(User user) {
		Volunteer volunteer = this.volunteerRepository.findByEmail(user.getEmail()).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

		volunteer.setName(user.getName());
		volunteer.setEmail(user.getEmail());
		volunteer.setPassword(user.getPassword());
		volunteer.setPhone(user.getPhone());

		this.volunteerRepository.save(volunteer);

	}

}
