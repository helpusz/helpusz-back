package br.com.helpusz.Utils;

import org.springframework.http.HttpStatus;
import org.apache.commons.validator.routines.EmailValidator;

import br.com.helpusz.exception.HelpuszException;
import lombok.Data;

@Data
public class Email {

	private String address;

	public Email() {
	}

	public Email(String address) {
		if(!validateEmail(address)) {
			throw new HelpuszException("Email inválido", HttpStatus.BAD_REQUEST);
		}

		this.address = address;
	}

	private boolean validateEmail(String address) {
		if(!EmailValidator.getInstance().isValid(address)) {
			return false;
		}

		return true;
	}

}
