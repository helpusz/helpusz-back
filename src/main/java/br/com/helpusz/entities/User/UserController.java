package br.com.helpusz.entities.User;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.helpusz.Utils.DonationItem;
import br.com.helpusz.Utils.Email;
import br.com.helpusz.Utils.SocialLinks;
import br.com.helpusz.entities.Activity.Activity;
import br.com.helpusz.entities.Activity.ActivityRepository;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/user")
@Tag(name = "User Controller", description = "Usuários")
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping("/register")
  @Operation(summary = "Criar um novo usuário")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados do usuário")
  public ResponseEntity register(@RequestBody User user) {
    this.userService.register(user);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/getToken")
  @Operation(summary = "Autenticar um usuário e gerar um token")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Credenciais do usuário")
  public ResponseEntity login(@RequestBody User user) {
    String token = userService.getToken(user);
    return ResponseEntity.ok(token);
  }

  @PatchMapping("/update")
  @Operation(summary = "Atualizar informações do usuário")
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Dados atualizados do usuário")
  public ResponseEntity update(@RequestBody User user) {
    userService.update(user);
    return ResponseEntity.ok().build();
  }

  @GetMapping("/getByEmail")
  @Operation(summary = "Buscar um usuário pelo email")
  @Parameter(name = "email", description = "Endereço de e-mail do usuário", required = true)
  public ResponseEntity getByEmail(@RequestParam Email email) {
    User user = userService.getByEmail(email);
    return ResponseEntity.ok(user);
  }

	@GetMapping("/volunteers/{activityId}")
	public ResponseEntity<List<User>> getAllVolunteers(@PathVariable String activityId) {
		List<User> volunteers = userService.getAllVolunteers(activityId);
		System.out.println(volunteers);
		return ResponseEntity.ok(volunteers);
	}

  @PatchMapping("/updateSocialLinks")
  @Operation(summary = "Atualizar redes sociais do usuário")
  @Parameter(name = "userId", description = "ID do usuário", required = true)
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Novos links sociais do usuário")
  public ResponseEntity updateSocialLinks(@RequestParam String userId, @RequestBody SocialLinks socialLinks) {
    userService.updateSocialLinks(userId, socialLinks);
    return ResponseEntity.ok().build();
  }

  @PostMapping("/{id}/profile-photo")
  @Operation(summary = "Fazer upload da foto de perfil do usuário")
  @Parameter(name = "id", description = "ID do usuário", required = true)
  @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Arquivo da foto de perfil")
  public ResponseEntity<String> uploadProfilePhoto(@PathVariable String id, @RequestParam("file") MultipartFile file) {
    try {
      String profilePhotoUrl = userService.uploadProfilePhoto(id, file);
      return ResponseEntity.ok(profilePhotoUrl);
    }
    catch(Exception e) {
      return ResponseEntity.badRequest().body(e.getMessage());
    }
  }

	@PostMapping("/{userId}/donations")
    public ResponseEntity<User> addDonationItem(@PathVariable String userId, @RequestBody DonationItem item) {
			User updatedUser = userService.addDonationItem(userId, item);
			return ResponseEntity.ok(updatedUser);
	}
}

