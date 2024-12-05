package br.com.helpusz.entities.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.User;
import br.com.helpusz.entities.User.UserRepository;
import br.com.helpusz.exception.HelpuszException;

@Service
public class OngServiceImpl implements OngService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public void validate(User ong, String validationCode) {
		if(ong.isValid()) {
			throw new HelpuszException("Ong já validado", HttpStatus.CONFLICT);
		}

		if(!ong.getValidationCode().equals(validationCode)) {
			throw new HelpuszException("Código inválido", HttpStatus.BAD_REQUEST);
		}

		if(ong.getValidationCode().equals(validationCode)) {
			ong.setValid(true);
		}

		this.userRepository.save(ong);
	}

}
