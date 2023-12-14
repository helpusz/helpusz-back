package br.com.helpusz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.model.Volunteer;
import br.com.helpusz.service.VolunteerService;

@RestController
@RequestMapping("/volunteer")
public class VolunteerController {
  
  @Autowired
  VolunteerService volunteerService;

  @PostMapping("/create")
  public ResponseEntity<Void> create(@RequestBody Volunteer volunteer) {
    this.volunteerService.create(volunteer);

    return ResponseEntity.ok().build();
  }
}
