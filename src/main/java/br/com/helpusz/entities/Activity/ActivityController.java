package br.com.helpusz.entities.Activity;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/activity")
public class ActivityController {

	@Autowired
	private ActivityService activityService;

	@PostMapping("/create")
  public ResponseEntity create(@RequestBody Activity activity) {
    this.activityService.create(activity);

    return ResponseEntity.ok().build();
  }


}
