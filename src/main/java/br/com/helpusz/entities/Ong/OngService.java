package br.com.helpusz.entities.Ong;

import org.springframework.stereotype.Service;

import br.com.helpusz.entities.User.User;

@Service
public interface OngService {

	void validate(User ong, String validationCode);

}
