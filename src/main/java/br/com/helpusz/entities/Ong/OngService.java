package br.com.helpusz.entities.Ong;

import java.util.List;

import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.User;

@Service
public interface OngService {

	List<User> getAllOngs();

	List<User> getAllOngsByCategory(OngCategoryEnum category);

	void validate(User ong, String validationCode);

}
