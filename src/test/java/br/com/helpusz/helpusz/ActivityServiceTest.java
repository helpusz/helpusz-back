package br.com.helpusz.helpusz;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.entities.Activity.Activity;
import br.com.helpusz.entities.Activity.ActivityRepository;
import br.com.helpusz.entities.Activity.ActivityServiceImpl;
import br.com.helpusz.entities.Activity.ActivityStatusEnum;
import br.com.helpusz.entities.Ong.OngCategoryEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;
import br.com.helpusz.services.S3Service;

import java.util.*;

@ExtendWith(MockitoExtension.class)
class ActivityServiceImplTest {

	@Mock
	private ActivityRepository activityRepository;

	@Mock
	private UserRepository userRepository;

	@Mock
	private S3Service s3Service;

	@InjectMocks
	private ActivityServiceImpl activityService;

	private User ong;
	private Activity activity;

	@BeforeEach
	void setUp() {
		ong = new User();
		ong.setId("ong123");
		Email email = new Email();
		email.setAddress("ong@email.com");
		ong.setEmail(email);
		ong.setCategory(OngCategoryEnum.EDUCACAO);

		activity = new Activity();
		activity.setId("act123");
		activity.setName("Ação Social");
		activity.setOngId("ong123");
		activity.setVolunteers(new ArrayList<>());
		activity.setQuantityVolunteersAvailable(10);
		activity.setLimitInscriptionDate(new Date(System.currentTimeMillis() + 86400000));
		activity.setActitivityStatusEnum(ActivityStatusEnum.ACTIVE);
	}

	@Test
	void createActivity() {
		when(userRepository.findById("ong123")).thenReturn(Optional.of(ong));
		when(activityRepository.save(any(Activity.class))).thenReturn(activity);

		activityService.create("ong123", activity);

		assertEquals("ong123", activity.getOngId());
		assertEquals(OngCategoryEnum.EDUCACAO, activity.getOngCategory());
		assertEquals(ActivityStatusEnum.ACTIVE, activity.getActitivityStatusEnum());
		verify(activityRepository).save(activity);
	}

	@Test
	void createActivityWhenOngNotFound() {
		when(userRepository.findById("ong123")).thenReturn(Optional.empty());

		HelpuszException exception = assertThrows(HelpuszException.class, () ->
			activityService.create("ong123", activity)
		);

		assertEquals("ONG não encontrada", exception.getMessage());
		assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
	}

	@Test
	void enterIntoActivity() {
		User volunteer = new User();
		volunteer.setId("vol123");
		when(userRepository.findById("vol123")).thenReturn(Optional.of(volunteer));
		when(activityRepository.findById("act123")).thenReturn(Optional.of(activity));
		when(activityRepository.save(any(Activity.class))).thenReturn(activity);

		activityService.enterIntoActivity("vol123", "act123");

		assertTrue(activity.getVolunteers().contains("vol123"));
		assertEquals(9, activity.getQuantityVolunteersAvailable());
	}

	@Test
	void enterIntoActivityWhenHasNoVacancies() {
		activity.setQuantityVolunteersAvailable(0);
		when(activityRepository.findById("act123")).thenReturn(Optional.of(activity));
		when(userRepository.findById("vol123")).thenReturn(Optional.of(new User()));

		HelpuszException exception = assertThrows(HelpuszException.class, () ->
				activityService.enterIntoActivity("vol123", "act123")
		);

		assertEquals("Não há mais vagas disponíveis para essa atividade", exception.getMessage());
	}

	@Test
	void deleteActivityWhithoutPermission() {
		User user = new User();
		user.setId("user456");
		when(userRepository.findByEmail(user.getEmail())).thenReturn(Optional.of(user));
		when(activityRepository.findById("act123")).thenReturn(Optional.of(activity));

		IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () ->
				activityService.delete(user, activity)
		);

		assertEquals("Você não tem permissão para deletar essa atividade", exception.getMessage());
	}

	@Test
	void uploadActivityImage() throws Exception {
		MultipartFile file = mock(MultipartFile.class);
		when(activityRepository.findById("act123")).thenReturn(Optional.of(activity));
		when(s3Service.uploadFile(file, "activity-photos")).thenReturn("https://s3.com/image.jpg");

		String imageURL = activityService.uploadActivityImage("act123", file);

		assertEquals("https://s3.com/image.jpg", imageURL);
		verify(activityRepository).save(activity);
	}
}
