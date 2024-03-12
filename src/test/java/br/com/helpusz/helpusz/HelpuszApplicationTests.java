package br.com.helpusz.helpusz;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;

import br.com.helpusz.HelpuszApplication;

@SpringBootTest
@ContextConfiguration(classes = {HelpuszApplication.class, TestConfiguration.class})
class HelpuszApplicationTests {

	@Test
	void contextLoads() {
	}

}
