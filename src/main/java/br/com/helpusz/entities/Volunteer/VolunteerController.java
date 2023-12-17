package br.com.helpusz.entities.Volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
  
  @Autowired
  private VolunteerService volunteerService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody Volunteer volunteer) {
    this.volunteerService.register(volunteer);
    
    return ResponseEntity.ok().build();
  }

  @PostMapping("/login")
  public ResponseEntity login(@RequestBody Volunteer volunteer) {
    String token = volunteerService.login(volunteer);
      
    return ResponseEntity.ok(token);
  }
  
  @GetMapping("/ping")
  public String ping() {
    return "pong";
  }
  
}
