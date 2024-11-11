package br.com.helpusz.entities.Ong;

import org.springframework.stereotype.Service;

@Service
public interface OngService {

	void validate(Ong ong, String validationCode);

}
