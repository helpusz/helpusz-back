package br.com.helpusz.entities.Activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.EnterActivityRequest;
import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/activity")
@Tag(name = "Activity Controller", description = "Atividades")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityRepository activityRepository;

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/create/{ongId}")
	@Operation(summary = "Criar uma nova atividade para uma ONG")
	@Parameter(name = "ongId", description = "ID da ONG", required = true)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da atividade")
	public ResponseEntity create(@PathVariable String ongId, @RequestBody Activity activity) {
		this.activityService.create(ongId, activity);
		return ResponseEntity.ok().build();
	}

	@PostMapping("/enterIntoActivity")
	@Operation(summary = "Inscrever um usuário em uma atividade")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados de inscrição na atividade")
	public ResponseEntity<?> enterIntoActivity(@RequestBody EnterActivityRequest request) {
		this.activityService.enterIntoActivity(request.getUserId(), request.getActivityId());
		return ResponseEntity.ok("Inscrição feita com sucesso.");
	}

	@GetMapping("/{id}")
	@Operation(summary = "Buscar uma atividade pelo ID")
	@Parameter(name = "id", description = "ID da atividade", required = true)
	public ResponseEntity<Activity> getById(@PathVariable String id) {
		Activity activity = this.activityRepository.findById(id).orElseThrow(() -> new HelpuszException("Atividade não encontrada", HttpStatus.NOT_FOUND));
		return ResponseEntity.ok(activity);
	}

	@GetMapping("/getAll")
	@Operation(summary = "Listar todas as atividades")
	public ResponseEntity<List<Activity>> getAll() {
		List<Activity> activities = this.activityService.getAll();
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/getAllByOngCategory")
	@Operation(summary = "Listar todas as atividades por categoria")
	@Parameter(name = "category", description = "Categoria da ONG", required = true)
	public ResponseEntity<List<Activity>> getAllByOngCategory(@RequestParam OngCategoryEnum category) {
		List<Activity> activities = this.activityService.getAllByOngCategory(category);
		return ResponseEntity.ok(activities);
	}

	@GetMapping("/getActivitiesRegisteredByVolunteerId/{userId}")
	@Operation(summary = "Listar atividades em que um voluntário está inscrito")
	@Parameter(name = "userId", description = "ID do voluntário", required = true)
	public List<Activity> getActivitiesRegisteredByVolunteerId(@PathVariable String userId) {
		return activityService.getActivitiesRegisteredByVolunteerId(userId);
	}

	@GetMapping("/getAllByOngId/{ongId}")
	@Operation(summary = "Listar todas as atividades de uma ONG pelo ID")
	@Parameter(name = "ongId", description = "ID da ONG", required = true)
	public List<Activity> getAllByOngId(@PathVariable String ongId) {
		return activityService.getAllByOngId(ongId);
	}



	@PostMapping("/{id}/activity-image")
	@Operation(summary = "Fazer upload da imagem de uma atividade")
	@Parameter(name = "id", description = "ID da atividade", required = true)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Arquivo da imagem da atividade")
	public ResponseEntity<String> uploadActivityImage(@PathVariable String id, @RequestParam("file") MultipartFile file) {
		try {
			String imageURL = activityService.uploadActivityImage(id, file);
			return ResponseEntity.ok(imageURL);
		} catch(Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@PutMapping("/{id}")
	@Operation(summary = "Atualizar uma atividade")
	@Parameter(name = "id", description = "ID da atividade", required = true)
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados da atividade a ser atualizada")
	public ResponseEntity<String> update(@PathVariable String id, @RequestBody Activity activity, @RequestParam String userEmail) {
			Email email = new Email();
			email.setAddress(userEmail);

			try {
				User user = this.userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));
				this.activityService.update(user, activity);
				return ResponseEntity.ok("Atividade atualizada com sucesso.");
			}
			catch (Exception e) {
				return ResponseEntity.badRequest().body(e.getMessage());
			}
	}

	@DeleteMapping("/{id}")
	@Operation(summary = "Deletar uma atividade")
	@Parameter(name = "id", description = "ID da atividade", required = true)
	public ResponseEntity<String> delete(@PathVariable String id, @RequestParam String userEmail) {
		Email email = new Email();
		email.setAddress(userEmail);

		try {
			User user = this.userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("ONG não encontrada"));
			Activity activity = new Activity();
			this.activityService.delete(user, activity);
			return ResponseEntity.ok("Atividade deletada com sucesso.");
		}
		catch (Exception e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}
}
