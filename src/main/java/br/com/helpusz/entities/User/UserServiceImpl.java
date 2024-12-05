package br.com.helpusz.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import br.com.helpusz.config.JwtTokenProvider;

import br.com.helpusz.exception.HelpuszException;

@Service
public class UserServiceImpl implements UserService {

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

  public void registerVolunteer(User user) {
    if(this.userRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Já existe uma conta Voluntário com esse email", HttpStatus.CONFLICT);
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    if(user.getTypeAccount().equals(TypeAccountEnum.VOLUNTEER)) {
			User volunteer = new User(user.getName(),user.getEmail(), user.getPassword(), user.getPhone());
			this.userRepository.save(volunteer);
		}
		else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
			User ong = new User(user.getName(),user.getEmail(), user.getPassword(), user.getCnpj());
			this.userRepository.save(ong);
		}
  }


  @Override
  public String getToken(User user) {
    if(!userRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND);
    }

		User exitingUser = userRepository.findByEmail(user.getEmail()).orElseThrow(() -> new IllegalArgumentException("Usuário não encontrado"));

		verifyPassword(user.getPassword(), exitingUser.getPassword());

    String token = jwtTokenProvider.createToken(user.getEmail().getAddress());

    System.out.println("Login efetuado");

    System.out.println("Token: " + token);

    return token;
  }

  private void verifyPassword(String password, String existingPassword) {
    if(!passwordEncoder.matches(password, existingPassword)) {
			throw new HelpuszException("Senha inválida", HttpStatus.UNAUTHORIZED);
    }
  }

	@Override
	public void update(User user) {
		// IMPLEMENTAR
	}


	@Override
	public void register(User user) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'register'");
	}


	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		throw new UnsupportedOperationException("Unimplemented method 'getByEmail'");
	}

}
