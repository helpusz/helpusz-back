package br.com.helpusz.entities.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;

import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("/user")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  public ResponseEntity register(@RequestBody User user) {
    this.userService.register(user);

    return ResponseEntity.ok().build();
  }

  @PostMapping("/getToken")
  public ResponseEntity login(@RequestBody User user) {
    String token = userService.getToken(user);

    return ResponseEntity.ok(token);
  }

	@PatchMapping("/update")
	public ResponseEntity update(@RequestBody User user) {
		userService.update(user);

		return ResponseEntity.ok().build();
	}

	@GetMapping("/getByEmail")
	public ResponseEntity getByEmail(@RequestParam Email email) {
		User user = userService.getByEmail(email);

		return ResponseEntity.ok(user);
	}

	@PatchMapping("/updateSocialLinks")
	public ResponseEntity updateSocialLinks(@RequestParam String userId, @RequestBody SocialLinks socialLinks) {
		userService.updateSocialLinks(userId, socialLinks);

		return ResponseEntity.ok().build();
	}
}
