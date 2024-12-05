package br.com.helpusz.entities.Volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.entities.User.User;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {

	@Autowired
	private VolunteerService volunteerService;


}
