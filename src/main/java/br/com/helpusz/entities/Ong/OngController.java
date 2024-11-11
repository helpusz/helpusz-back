package br.com.helpusz.entities.Ong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/ong")
public class OngController {

	@Autowired
	private OngService ongService;

	@PostMapping("/validate")
  public void validate(@AuthenticationPrincipal Ong ong, @RequestBody String validationCode) {
    this.ongService.validate(ong, validationCode);
  }



}
