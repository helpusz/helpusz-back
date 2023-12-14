package br.com.helpusz.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.model.Volunteer;
import br.com.helpusz.service.VolunteerService;
import org.springframework.web.bind.annotation.GetMapping;


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

  @GetMapping("/read/all")
  public ResponseEntity<List<Volunteer>> getAllVolunteers() {
    List<Volunteer> volunteers = volunteerService.findAll();
    return ResponseEntity.ok(volunteers);
  }
}
