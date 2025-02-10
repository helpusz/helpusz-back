package br.com.helpusz.helpusz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.config.JwtTokenProvider;
import br.com.helpusz.entities.User.TypeAccountEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.entities.User.UserServiceImpl;
import br.com.helpusz.exception.HelpuszException;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private PasswordEncoder passwordEncoder;

	@InjectMocks
	private UserServiceImpl userService;

	@Mock
	private JwtTokenProvider jwtTokenProvider;

	@Test
	void registerWhenEmailAlreadyExists() {
		Email email = new Email();
		email.setAddress("luchkasz@protonmail.com");

		User user = new User("Lucas", email, "senha", TypeAccountEnum.VOLUNTEER, "41991653248");

		when(userRepository.existsByEmail(email)).thenReturn(true);

		HelpuszException exception = assertThrows(HelpuszException.class, () -> userService.register(user));
		assertEquals("Já existe uma conta com esse email", exception.getMessage());
		assertEquals(HttpStatus.CONFLICT, exception.getStatus());
	}

	@Test
	void updateUser() {
		Email email = new Email();
		email.setAddress("luchkasz@protonmail.com");

		User user = new User("Lucas", email, "password123", TypeAccountEnum.VOLUNTEER, "41991653248");

		when(userRepository.save(user)).thenReturn(user);

		userService.update(user);

		verify(userRepository, times(1)).save(user);
	}

	@Test
	void getByEmailWhenUserExists() {
		Email email = new Email();
		email.setAddress("luchkasz@protonmail.com");

		User user = new User("Lucas", email, "password123", TypeAccountEnum.VOLUNTEER, "41991653248");

		when(userRepository.findByEmail(email)).thenReturn(Optional.of(user));

		User result = userService.getByEmail(email);

		assertNotNull(result);
		assertEquals(user.getEmail(), result.getEmail());
	}

	@Test
	void getByEmailWhenUserNotFound() {
		Email email = new Email();
		email.setAddress("luchkasz@protonmail.com");

		when(userRepository.findByEmail(email)).thenReturn(Optional.empty());

		HelpuszException exception = assertThrows(HelpuszException.class, () -> userService.getByEmail(email));

		assertEquals("Usuário não encontrado", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}

	@Test
	void getTokenWhenUserNotFound() {
		Email email = new Email();
		email.setAddress("luchkasz@protonmail.com");
		User user = new User("Lucas", email, passwordEncoder.encode("password123"), TypeAccountEnum.VOLUNTEER, "41991653248");

		when(userRepository.existsByEmail(email)).thenReturn(false);

		HelpuszException exception = assertThrows(HelpuszException.class, () -> userService.getToken(user));

		assertEquals("Usuário não encontrado", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
}
