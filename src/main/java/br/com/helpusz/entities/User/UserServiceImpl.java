package br.com.helpusz.entities.User;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpusz.config.JwtTokenProvider;
import br.com.helpusz.entities.Ong.OngRepository;
import br.com.helpusz.entities.Volunteer.Volunteer;
import br.com.helpusz.entities.Ong.Ong;
import br.com.helpusz.entities.Volunteer.VolunteerRepository;


@Service
public class UserServiceImpl implements UserService {

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private JwtTokenProvider jwtTokenProvider;
  
  @Autowired
  private VolunteerRepository volunteerRepository;

  @Autowired
  private OngRepository ongRepository;
  
  @Override
  public void register(User user) {
    if(user.getTypeAccount().equals(TypeAccountEnum.VOLUNTEER)) {
      Volunteer volunteer = (Volunteer) user;
      
      if(this.volunteerRepository.existsByEmail(volunteer.getEmail())) {
        throw new RuntimeException("Já existe uma conta Voluntário com esse email");
      } 

      volunteer.setPassword(passwordEncoder.encode(volunteer.getPassword()));
      
      this.volunteerRepository.save(volunteer);
    }

    else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
      Ong ong = (Ong) user;

      if(this.ongRepository.existsByEmail(ong.getEmail())) {
        throw new RuntimeException("Já existe uma conta Ong com esse email");
      }

      ong.setPassword(passwordEncoder.encode(ong.getPassword()));

      this.ongRepository.save(ong);
    }
  }

  @Override
  public String getToken(User user) {
    if(user.getTypeAccount().equals(TypeAccountEnum.VOLUNTEER)) {
      validateVolunteer((Volunteer) user);
    }
    
    else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
      validateOng((Ong) user);
    }

    String token = jwtTokenProvider.createToken(user.getEmail());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }

  private void validateVolunteer(Volunteer volunteer) {
    if(!volunteerRepository.existsByEmail(volunteer.getEmail())) {
      throw new RuntimeException("Conta de Voluntário não encontrada");
    }

    if(!passwordEncoder.matches(volunteer.getPassword(), volunteer.getPassword())) {
      throw new RuntimeException("Senha de Voluntário inválida");
    }
  }

  private void validateOng(Ong ong) {
    if(!ongRepository.existsByEmail(ong.getEmail())) {
      throw new RuntimeException("Conta de Ong não encontrada");
    }

    if(!passwordEncoder.matches(ong.getPassword(), ong.getPassword())) {
      throw new RuntimeException("Senha de Ong inválida");
    }
  }
  
}
