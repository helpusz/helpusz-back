package br.com.helpusz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.model.Volunteer;
import br.com.helpusz.repository.VolunteerRepository;

@RestController
@RequestMapping("/authorization")
public class AuthorizationController {
  @Autowired
  VolunteerRepository volunteerRepository;
  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Volunteer volunteer) {
    if(this.volunteerRepository.findByEmail(volunteer.getEmail()) != null) {
      return ResponseEntity.badRequest().build();
    }

    String encryptedPassword = new BCryptPasswordEncoder().encode(volunteer.getPassword());

    Volunteer newVolunteer = new Volunteer(volunteer.getName(), volunteer.getPhone(), volunteer.getEmail(), encryptedPassword);

    this.volunteerRepository.save(newVolunteer);

    return ResponseEntity.ok().build();
  }

@PostMapping("/login")
public ResponseEntity login(@RequestBody Volunteer loginRequest) {
	UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword());
	
	Volunteer existingVolunteer = volunteerRepository.findByEmail(loginRequest.getEmail());

	if(existingVolunteer == null) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}
	
	boolean passwordMatch = new BCryptPasswordEncoder().matches(loginRequest.getPassword(),existingVolunteer.getPassword());

	if(!passwordMatch) {
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
	}

	return ResponseEntity.ok().build();
}
}
