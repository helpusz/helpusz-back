package br.com.helpusz;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.helpusz.model.Volunteer;
import br.com.helpusz.service.VolunteerService;

@SpringBootApplication
public class HelpuszApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(HelpuszApplication.class, args);
	}

	@Autowired
	private VolunteerService volunteerService;

	Volunteer volunteer = new Volunteer("Lucas", "41991653248", "silva.lucasgomes@protonmail.com", "Senha123");

	@Override
	public void run(String... args) throws Exception {
			this.volunteerService.save(this.volunteer);
	}
}
