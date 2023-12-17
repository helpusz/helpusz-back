package br.com.helpusz.entities.Volunteer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpusz.config.JwtTokenProvider;


@Service
public class VolunteerServiceImpl implements VolunteerService {

  @Autowired
  private VolunteerRepository volunteerRepository;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  @Override
  public void register(Volunteer volunteer) {
    if(this.volunteerRepository.existsByEmail(volunteer.getEmail())) {
      throw new RuntimeException("Conta já existente");
    } 

    volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
    
    this.volunteerRepository.save(volunteer);
  }

  @Override
  public String login(Volunteer volunteer) {
    if(!this.volunteerRepository.existsByEmail(volunteer.getEmail())) {
      throw new RuntimeException("Conta não existente");
    }

    Volunteer existingOng = this.volunteerRepository.findByEmail(volunteer.getEmail());

    if(!passwordEncoder.matches(volunteer.getPassword(), existingOng.getPassword())) {
      throw new RuntimeException("Senha inválida");
    }

    String token = jwtTokenProvider.createToken(existingOng.getEmail());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }
  
}
