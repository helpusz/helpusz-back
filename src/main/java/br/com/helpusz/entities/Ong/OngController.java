package br.com.helpusz.entities.Ong;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import br.com.helpusz.entities.User.User;

@RestController
@RequestMapping("/ong")
public class OngController {

	@Autowired
	private OngService ongService;

	@GetMapping("/getAll")
  public List<User> getAllOngs() {
    return this.ongService.getAllOngs();
  }

	@GetMapping("/getAllByCategory")
	public List<User> getAllByCategory(@RequestParam OngCategoryEnum category) {
		return this.ongService.getAllOngsByCategory(category);
	}

	@PostMapping("/validate")
  public void validate(@AuthenticationPrincipal User ong, @RequestBody String validationCode) {
    this.ongService.validate(ong, validationCode);
  }

}
