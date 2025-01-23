package br.com.helpusz.Utils;

import org.springframework.http.HttpStatus;
import org.apache.commons.validator.routines.RegexValidator;

import br.com.helpusz.exception.HelpuszException;
import lombok.Data;

@Data
public class CNPJ {

	private String number;

	public CNPJ(String number) {
		if(!validateCNPJ(number)) {
			throw new HelpuszException("CNPJ inválido", HttpStatus.BAD_REQUEST);
		}

		this.number = number;
	}

	private boolean validateCNPJ(String cnpj) {
    RegexValidator cnpjValidator = new RegexValidator("^\\d{14}$");
    return cnpjValidator.isValid(cnpj);
	}

}
