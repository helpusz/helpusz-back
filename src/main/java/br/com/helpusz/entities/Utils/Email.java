package br.com.helpusz.entities.Utils;

import org.springframework.http.HttpStatus;

import br.com.helpusz.exception.HelpuszException;
import lombok.Data;

@Data
public class Email {

	private String address;

	public Email(String address) {
		if(!validateEmail(address)) {
			throw new HelpuszException("Email inválido", HttpStatus.BAD_REQUEST);
		}

		this.address = address;
	}

	private boolean validateEmail(String address) {
		if(!address.contains("@") || !address.contains(".com")) {
			return false;
		}

		return true;
	}

}
