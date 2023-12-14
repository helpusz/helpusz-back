package br.com.helpusz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.helpusz.model.Volunteer;
import br.com.helpusz.repository.VolunteerRepository;

@Service
public class VolunteerService {

  @Autowired
  private VolunteerRepository volunteerRepository;

  public void save(Volunteer volunteer) {
    volunteerRepository.save(volunteer);
  }
  
}
