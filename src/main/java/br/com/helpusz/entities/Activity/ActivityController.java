package br.com.helpusz.entities.Activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.Utils.EnterActivityRequest;
import br.com.helpusz.exception.HelpuszException;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityRepository activityRepository;

	@PostMapping("/create/{ongId}")
  public ResponseEntity create(@PathVariable String ongId, @RequestBody Activity activity) {
    this.activityService.create(ongId, activity);

    return ResponseEntity.ok().build();
  }

	@PostMapping("/enterIntoActivity")
	public ResponseEntity<?> enterIntoActivity(@RequestBody EnterActivityRequest request) {
    this.activityService.enterIntoActivity(request.getUserId(), request.getActivityId());
    return ResponseEntity.ok("Inscrição feita com sucesso.");
	}


	@GetMapping("/{id}")
	public ResponseEntity<Activity> getById(@PathVariable String id) {
		Activity activity = this.activityRepository.findById(id).orElseThrow(() -> new HelpuszException("Atividade não encontrada", HttpStatus.NOT_FOUND));

		return ResponseEntity.ok(activity);
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<Activity>> getAll() {
		List<Activity> activities = this.activityService.getAll();

		return ResponseEntity.ok(activities);
	}

	@GetMapping("/getAllByOngCategory")
	public ResponseEntity<List<Activity>> getAllByOngCategory(@RequestParam OngCategoryEnum category) {
		List<Activity> activities = this.activityService.getAllByOngCategory(category);

		return ResponseEntity.ok(activities);
	}

}
