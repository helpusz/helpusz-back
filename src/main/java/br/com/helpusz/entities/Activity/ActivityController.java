package br.com.helpusz.entities.Activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.entities.Ong.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/create")
  public ResponseEntity create(@AuthenticationPrincipal Ong ong, @RequestBody Activity activity) {
    this.activityService.create(ong, activity);

    return ResponseEntity.ok().build();
  }

	@GetMapping("/getAll")
	public ResponseEntity getAll() {
		this.activityService.getAll();

		return ResponseEntity.ok().build();
	}

}
