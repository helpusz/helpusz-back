package br.com.helpusz.entities.User;

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
      if(this.volunteerRepository.existsByEmail(user.getEmail())) {
        throw new RuntimeException("Já existe uma conta Voluntário com esse email");
      } 

      user.setPassword(passwordEncoder.encode(user.getPassword()));
      
      Volunteer volunteer = new Volunteer(user.getEmail(), user.getPassword(), user.getTypeAccount());
      
      this.volunteerRepository.save(volunteer);
    }

    else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
      if(this.ongRepository.existsByEmail(user.getEmail())) {
        throw new RuntimeException("Já existe uma conta Ong com esse email");
      }

      user.setPassword(passwordEncoder.encode(user.getPassword()));

      Ong ong = new Ong(user.getEmail(), user.getPassword(), user.getTypeAccount());
      
      this.ongRepository.save(ong);
    }
  }

  @Override
  public String getToken(User user) {
    this.validateUser(user);

    String token = jwtTokenProvider.createToken(user.getEmail());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }

  private void validateUser(User user) {
    if(!volunteerRepository.existsByEmail(user.getEmail())) {
      throw new RuntimeException("Usuário não encontrado");
    }
    
    if(user.getTypeAccount().equals(TypeAccountEnum.VOLUNTEER)) {
      Volunteer existingVolunteer = this.volunteerRepository.findByEmail(user.getEmail());

      this.verifyPassword(user.getPassword(), existingVolunteer.getPassword());
    }

    else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
      Ong existingOng = this.ongRepository.findByEmail(user.getEmail());

      this.verifyPassword(user.getPassword(), existingOng.getPassword());
    }

  }

  private void verifyPassword(String password, String existingPassword) {
    if(!passwordEncoder.matches(password, existingPassword)) {
        throw new RuntimeException("Senha inválida");
    }
  }

}
