package br.com.helpusz.entities.Activity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;
import br.com.helpusz.services.S3Service;

@Service
public class ActivityServiceImpl  implements ActivityService {

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
  private S3Service s3Service;

	@Override
	public void create(String ongId, Activity activity) {
		User ong = this.userRepository.findById(ongId).orElseThrow(() -> new HelpuszException("ONG não encontrada", HttpStatus.NOT_FOUND));

		activity.setOngId(ongId);
		activity.setOngCategory(ong.getCategory());
		activity.setVolunteers(new ArrayList<>());
		activity.setActitivityStatusEnum(ActivityStatusEnum.ACTIVE);
		activity.setActivityVisibilityEnum(ActivityVisibilityEnum.VISIBLE);

		this.activityRepository.save(activity);
	}

	@Override
	public void update(User user, Activity activity) {
		Activity activityToUpdate = this.activityRepository.findById(activity.getId()).orElseThrow(() -> new IllegalArgumentException("Atividade não encontrada"));

		User ong = this.userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));

		if(!activityToUpdate.getOngId().equals(ong.getId())) {
			throw new IllegalArgumentException("Você não tem permissão para atualizar essa atividade");
		}

		activityToUpdate.setName(activity.getName());
		activityToUpdate.setOngCategory(activity.getOngCategory());
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

		User ong = this.userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));

		if(!activityToDelete.getOngId().equals(ong.getId())) {
			throw new IllegalArgumentException("Você não tem permissão para deletar essa atividade");
		}

		this.activityRepository.delete(activity);
	}

	public void enterIntoActivity(String userId, String activityId) {
		User volunteer = this.userRepository.findById(userId).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

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

	@Override
	public List<Activity> getAll() {
		List<Activity> activities = this.activityRepository.findAll();
		if(activities.isEmpty()) {
			throw new HelpuszException("Não há nenhuma atividade cadastrada no momento", HttpStatus.NOT_FOUND);
		}

		return activities;
	}


	@Override
	public List<Activity> getAllByVolunteerId(User user) {
		User volunteer = this.userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

		return this.activityRepository.findAllByVolunteersContains(volunteer.getId());
	}

	@Override
	public List<Activity> getAllByOngCategory(OngCategoryEnum category) {
		boolean exists = this.activityRepository.existsByOngCategory(category);
		if(!exists) {
			throw new HelpuszException("Ainda não existe nenhuma atividade nessa categoria", HttpStatus.NOT_FOUND);
		}

		return this.activityRepository.findAllByOngCategory(category);
	}

	@Override
	public List<Activity> getActivitiesRegisteredByVolunteerId(String userId) {
		return activityRepository.findAllByVolunteersContaining(userId);
	}

	@Override
	public List<Activity> getAllByOngId(String ongId) {
		return activityRepository.findAllByOngId(ongId);
	}

	public String uploadActivityImage(String userId, MultipartFile file) throws Exception {
    Activity activity = activityRepository.findById(userId).orElseThrow(() -> new RuntimeException("Atividade não encontrada"));

    String imageURL = s3Service.uploadFile(file, "activity-photos");

    activity.setImageURL(imageURL);
    activityRepository.save(activity);

    return imageURL;
  }

}
