package br.com.helpusz.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;
import br.com.helpusz.config.JwtTokenProvider;
import br.com.helpusz.exception.HelpuszException;
import br.com.helpusz.services.S3Service;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
  private S3Service s3Service;

  private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

	@Autowired
	private UserRepository userRepository;

  public void register(User user) {
    if(this.userRepository.existsByEmail(user.getEmail())) {
      throw new HelpuszException("Já existe uma conta com esse email", HttpStatus.CONFLICT);
    }

    user.setPassword(passwordEncoder.encode(user.getPassword()));

    if(user.getTypeAccount().equals(TypeAccountEnum.VOLUNTEER)) {
			User volunteer = new User(user.getName(), user.getEmail(), user.getPassword(), user.getTypeAccount(), user.getPhone());
			this.userRepository.save(volunteer);
		}
		else if(user.getTypeAccount().equals(TypeAccountEnum.ONG)) {
			User ong = new User(user.getName(), user.getEmail(), user.getPassword(), user.getTypeAccount(), user.getCnpj(), user.getCategory());
			ong.setIsValid(false);
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
		userRepository.save(user);
	}

	@Override
	public User getByEmail(Email email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));
	}

	@Override
  public void updateSocialLinks(String userId, SocialLinks socialLinks) {
    User user = userRepository.findById(userId).orElseThrow(() -> new HelpuszException("Usuário não encontrado", HttpStatus.NOT_FOUND));

    user.setSocialLinks(socialLinks);

    userRepository.save(user);
  }

	public String uploadProfilePhoto(String userId, MultipartFile file) throws Exception {
    User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    String profilePhotoUrl = s3Service.uploadFile(file, "profile-photos");

    user.setProfilePhotoUrl(profilePhotoUrl);
    userRepository.save(user);

    return profilePhotoUrl;
  }
}
