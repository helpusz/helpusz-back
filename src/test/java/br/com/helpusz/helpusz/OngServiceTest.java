package br.com.helpusz.helpusz;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.Ong.OngServiceImpl;
import br.com.helpusz.entities.User.TypeAccountEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;


@ExtendWith(MockitoExtension.class)
public class OngServiceTest {

	@InjectMocks
	private OngServiceImpl ongService;

	@Mock
	private UserRepository userRepository;

	@Test
	void getAllOngsWhenOngsExist() {
		List<User> ongs = List.of(new User("ONG 1", new Email("ong1@email.com"), "senha", TypeAccountEnum.ONG, "123456789"));
		when(userRepository.findAllByTypeAccount(TypeAccountEnum.ONG)).thenReturn(ongs);

		List<User> result = ongService.getAllOngs();

		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("ONG 1", result.get(0).getName());
	}

	@Test
	void getAllOngsWhenNoOngsExist() {
		when(userRepository.findAllByTypeAccount(TypeAccountEnum.ONG)).thenReturn(Collections.emptyList());

		List<User> result = ongService.getAllOngs();

		assertTrue(result.isEmpty());
	}

	@Test
	void getByIdWhenOngExists() {
		User ong = new User("ONG 1", new Email("ong1@email.com"), "senha", TypeAccountEnum.ONG, "123456789");
		when(userRepository.findById("1")).thenReturn(Optional.of(ong));

		User result = ongService.getById("1");

		assertNotNull(result);
		assertEquals("ONG 1", result.getName());
	}

	@Test
	void getByIdWhenOngDoesNotExist() {
		when(userRepository.findById("1")).thenReturn(Optional.empty());

		HelpuszException exception = assertThrows(HelpuszException.class, () -> ongService.getById("1"));
		assertEquals("ONG não encontrada", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}

	@Test
	void getAllOngsByCategoryWhenOngsExist() {
		OngCategoryEnum category = OngCategoryEnum.EDUCACAO;
		List<User> ongs = List.of(new User("ONG Educação", new Email("edu@email.com"), "senha", TypeAccountEnum.ONG, "123456789"));

		when(userRepository.existsByCategory(category)).thenReturn(true);
		when(userRepository.findAllByCategory(category)).thenReturn(ongs);

		List<User> result = ongService.getAllOngsByCategory(category);

		assertFalse(result.isEmpty());
		assertEquals(1, result.size());
		assertEquals("ONG Educação", result.get(0).getName());
	}

	@Test
	void getAllOngsByCategoryWhenNoOngsExist() {
		OngCategoryEnum category = OngCategoryEnum.EDUCACAO;

		when(userRepository.existsByCategory(category)).thenReturn(false);

		HelpuszException exception = assertThrows(HelpuszException.class, () -> ongService.getAllOngsByCategory(category));
		assertEquals("Ainda não existe nenhuma ONG nessa categoria", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}
}
