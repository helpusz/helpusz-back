package br.com.helpusz.entities.Activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.entities.User.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/create/{ongId}")
  public ResponseEntity create(@PathVariable String ongId, @RequestBody Activity activity) {
    this.activityService.create(ongId, activity);

    return ResponseEntity.ok().build();
  }

	@PostMapping("/enterIntoActivity")
  public ResponseEntity enterIntoActivity(@RequestBody User user, String activityId) {
    this.activityService.enterIntoActivity(user, activityId);

    return ResponseEntity.ok().build();
  }

	@GetMapping("/getAll")
	public ResponseEntity<List<Activity>> getAll() {
		List<Activity> activities = this.activityService.getAll();

		return ResponseEntity.ok(activities);
	}

}
