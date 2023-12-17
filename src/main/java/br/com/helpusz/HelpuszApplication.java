package br.com.helpusz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"br.com.helpusz.helpusz.config", "br.com.helpusz.helpusz.entities.Ong"})
public class HelpuszApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpuszApplication.class, args);
	}

}
