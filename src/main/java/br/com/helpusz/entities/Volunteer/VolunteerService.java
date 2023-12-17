package br.com.helpusz.entities.Volunteer;

import org.springframework.stereotype.Service;

@Service
public interface VolunteerService {
  
  void register(Volunteer volunteer);

  String login(Volunteer volunteer);
  
}
