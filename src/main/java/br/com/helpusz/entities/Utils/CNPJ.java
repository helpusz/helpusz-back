package br.com.helpusz.entities.Utils;

import br.com.helpusz.exception.HelpuszException;
import lombok.Data;

@Data
public class CNPJ {
	
	private String number;

	public CNPJ(String number) {
		if(!validateCNPJ(number)) {
			throw new HelpuszException("CNPJ inválido");
		}

		this.number = number;
	}
	
	private boolean validateCNPJ(String number) {
		final int CNPJ_LENGTH = 14;

		if(number.length() != CNPJ_LENGTH) {
			return false;
		}

		for(char c : number.toCharArray()) {
			if(!Character.isDigit(c)) {
				return false;
			}
		}

		return true;
	}
	
}
