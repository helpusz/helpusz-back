package br.com.helpusz.entities.Volunteer;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.Activity.Activity;
import br.com.helpusz.entities.Activity.ActivityRepository;
import br.com.helpusz.entities.Activity.ActivityStatusEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.exception.HelpuszException;

@Service
public class VolunteerServiceImpl implements VolunteerService {

	@Autowired
	private VolunteerRepository volunteerRepository;

	@Autowired
	ActivityRepository activityRepository;

	public void update(User user) {
		Volunteer volunteer = this.volunteerRepository.findByEmail(user.getEmail()).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

		volunteer.setName(user.getName());
		volunteer.setEmail(user.getEmail());
		volunteer.setPassword(user.getPassword());
		volunteer.setPhone(user.getPhone());

		this.volunteerRepository.save(volunteer);
	}

	public void enterIntoActivity(User user, String activityId) {
		Volunteer volunteer = this.volunteerRepository.findByEmail(user.getEmail()).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

		Activity activity = this.activityRepository.findById(activityId).orElseThrow(() -> new HelpuszException("Atividade não encontrada", HttpStatus.NOT_FOUND));

		if(activity.getLimitInscriptionDate().before(new Date())) {
			throw new HelpuszException("A data limite para inscrição nessa atividade já foi atingida", HttpStatus.CONFLICT);
		}

		if(activity.getQuantityVolunteersAvailable().intValue() == 0) {
			throw new HelpuszException("Não há mais vagas disponíveis para essa atividade", HttpStatus.CONFLICT);
		}

		if(activity.getActitivityStatusEnum().equals(ActivityStatusEnum.INACTIVE)) {
			throw new HelpuszException("Essa atividade já está fechada", HttpStatus.CONFLICT);
		}

		if(activity.getVolunteers().contains(volunteer.getId())) {
			throw new HelpuszException("Você já está inscrito nessa atividade", HttpStatus.CONFLICT);
		}

		activity.getVolunteers().add(volunteer.getId());
		activity.setQuantityVolunteersAvailable(activity.getQuantityVolunteersAvailable().intValue() - 1);

		this.activityRepository.save(activity);
	}

}
