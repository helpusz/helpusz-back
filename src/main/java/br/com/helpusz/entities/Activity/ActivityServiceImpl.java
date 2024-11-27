package br.com.helpusz.entities.Activity;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.Ong.Ong;
import br.com.helpusz.entities.Ong.OngRepository;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.exception.HelpuszException;

@Service
public class ActivityServiceImpl  implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private OngRepository ongRepository;

	@Override
	public void create(@AuthenticationPrincipal Ong ong, Activity activity) {
		activity.setOngId(ong.getId());

		this.activityRepository.save(activity);
	}

	@Override
	public void update(User user, Activity activity) {
		Activity activityToUpdate = this.activityRepository.findById(activity.getId()).orElseThrow(() -> new IllegalArgumentException("Atividade não encontrada"));

		Ong ong = this.ongRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));

		if(!activityToUpdate.getOngId().equals(ong.getId())) {
			throw new IllegalArgumentException("Você não tem permissão para atualizar essa atividade");
		}

		activityToUpdate.setName(activity.getName());
		activityToUpdate.setDescription(activity.getDescription());
		activityToUpdate.setLocation(activity.getLocation());
		activityToUpdate.setStartDate(activity.getStartDate());
		activityToUpdate.setEndDate(activity.getEndDate());
		activityToUpdate.setLimitInscriptionDate(activity.getLimitInscriptionDate());
		activityToUpdate.setQuantityVolunteersAvailable(activity.getQuantityVolunteersAvailable());
		activityToUpdate.setActitivityStatusEnum(activity.getActitivityStatusEnum());
		activityToUpdate.setActivityVisibilityEnum(activity.getActivityVisibilityEnum());
		activityToUpdate.setTags(activity.getTags());
		activityToUpdate.setImageURL(activity.getImageURL());

		this.activityRepository.save(activityToUpdate);
	}

	@Override
	public void delete(User user, Activity activity) {
		Activity activityToDelete = this.activityRepository.findById(activity.getId()).orElseThrow(() -> new IllegalArgumentException("Atividade não encontrada"));

		Ong ong = this.ongRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));

		if(!activityToDelete.getOngId().equals(ong.getId())) {
			throw new IllegalArgumentException("Você não tem permissão para deletar essa atividade");
		}

		this.ongRepository.delete(ong);
	}

	@Override
	public List<Activity> getAll() {
		if(this.activityRepository.count() == 0) {
			throw new HelpuszException("Não há nenhuma atividade cadastrada no momento", HttpStatus.NOT_FOUND);
		}

		return this.activityRepository.findAll();
	}

}
