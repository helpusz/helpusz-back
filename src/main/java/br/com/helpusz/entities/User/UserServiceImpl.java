package br.com.helpusz.entities.User;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import br.com.helpusz.config.JwtTokenProvider;
import br.com.helpusz.entities.Ong.OngRepository;
import br.com.helpusz.entities.Volunteer.Volunteer;
import br.com.helpusz.entities.Ong.Ong;
import br.com.helpusz.entities.Volunteer.VolunteerRepository;
import br.com.helpusz.exception.HelpuszException;


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
    switch(user.getTypeAccount()) {
      case VOLUNTEER:
        registerVolunteer(user);
        break;

      case ONG:
        registerOng(user);
        break;
    }
  }

  public void registerVolunteer(User user) {
    if(this.volunteerRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Já existe uma conta Voluntário com esse email", HttpStatus.CONFLICT);
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Volunteer volunteer = new Volunteer(user.getName(),user.getEmail(), user.getPassword(), user.getPhone());

    this.volunteerRepository.save(volunteer);
  }

  public void registerOng(User user) {
    if(this.ongRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Já existe uma conta Ong com esse email", HttpStatus.CONFLICT);
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    Ong ong = new Ong(user.getName(), user.getEmail(), user.getPassword(), user.getCnpj());

		String verificationCode = String.format("%04d", new Random().nextInt(10000));

		ong.setValidationCode(verificationCode);

    this.ongRepository.save(ong);
  }

  @Override
  public String getToken(User user) {
    this.validateUser(user);

    String token = jwtTokenProvider.createToken(user.getEmail().getAddress());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }

  private void validateUser(User user) {
    if(!volunteerRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND);
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
        throw new HelpuszException("Senha inválida", HttpStatus.UNAUTHORIZED);
    }
  }

}
