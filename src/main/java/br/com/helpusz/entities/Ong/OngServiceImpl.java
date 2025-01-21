package br.com.helpusz.entities.Ong;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.TypeAccountEnum;
import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;

@Service
public class OngServiceImpl implements OngService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(User ong, String validationCode) {
		if(ong.getIsValid()) {
			throw new HelpuszException("Ong já validado", HttpStatus.CONFLICT);
		}

		if(!ong.getValidationCode().equals(validationCode)) {
			throw new HelpuszException("Código inválido", HttpStatus.BAD_REQUEST);
		}

		if(ong.getValidationCode().equals(validationCode)) {
			ong.setIsValid(true);
		}

		this.userRepository.save(ong);
	}

	@Override
	public List<User> getAllOngs() {
		return userRepository.findAllByTypeAccount(TypeAccountEnum.ONG);
	}

	@Override
	public User getById(String id) {
		return userRepository.findById(id).orElseThrow(() -> new HelpuszException("ONG não encontrada", HttpStatus.NOT_FOUND));
	}

	@Override
	public List<User> getAllOngsByCategory(OngCategoryEnum category) {
		System.out.println(category);
		boolean exists = userRepository.existsByCategory(category);
		if(!exists) {
			throw new HelpuszException("Ainda não existe nenhuma ONG nessa categoria", HttpStatus.NOT_FOUND);
		}

		return userRepository.findAllByCategory(category);
	}

}
