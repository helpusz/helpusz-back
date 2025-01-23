package br.com.helpusz.helpusz.entities.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.Test;

import br.com.helpusz.Utils.CNPJ;
import br.com.helpusz.exception.HelpuszException;

public class CNPJTest {

	@Test
	public void validCNPJ() {
		String validCNPJ = "30533826000187";

		CNPJ cnpj = new CNPJ(validCNPJ);

		assertEquals(validCNPJ, cnpj.getNumber());
	}

	@Test
	public void invalidCnpj() {
		String invalidCnpj = "3053382600018";

		assertThrows(HelpuszException.class, () -> new CNPJ(invalidCnpj));
	}

	@Test
	public void invalidCnpj2() {
		String invalidCnpj = "A0533826000187";

		assertThrows(HelpuszException.class, () -> new CNPJ(invalidCnpj));
	}

}
