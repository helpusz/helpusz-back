package br.com.helpusz.helpusz.entities.Utils;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

import br.com.helpusz.entities.Utils.Email;
import br.com.helpusz.exception.HelpuszException;

public class EmailTest {
	
	@Test
	public void validEmail() {
		String validEmail = "msftsrep@protonmail.com";

		Email email = new Email(validEmail);
		
		assertEquals(validEmail, email.getAddress());
	}

	@Test
	public void invalidEmail() {
		String invalidEmail = "syre@protonmail";
		
		assertThrows(HelpuszException.class, () -> new Email(invalidEmail));
	}

	@Test
	public void invalidEmail2() {
		String invalidEmail = "erysprotonmail.com";
		
		assertThrows(HelpuszException.class, () -> new Email(invalidEmail));
	}
}
