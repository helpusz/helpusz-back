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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/ong")
@Tag(name = "ONG Controller", description = "ONGs")
public class OngController {

	@Autowired
	private OngService ongService;

	@GetMapping("/getAll")
	@Operation(summary = "Obter todas as ONGs", description = "Retorna uma lista de todas as ONGs cadastradas.")
	public List<User> getAllOngs() {
		return this.ongService.getAllOngs();
	}

	@GetMapping("/getById")
	@Operation(summary = "Obter ONG por ID", description = "Retorna os detalhes de uma ONG específica pelo ID.")
	@Parameter(name = "id", description = "ID da ONG", required = true)
	public User getById(@RequestParam String id) {
		return this.ongService.getById(id);
	}

	@GetMapping("/getAllByCategory")
	@Operation(summary = "Obter ONGs por categoria", description = "Retorna uma lista de ONGs filtradas por categoria.")
	@Parameter(name = "category", description = "Categoria da ONG", required = true)
	public List<User> getAllByCategory(@RequestParam OngCategoryEnum category) {
		return this.ongService.getAllOngsByCategory(category);
	}

	@PostMapping("/validate")
	@Operation(summary = "Validar ONG", description = "Valida uma ONG com um código de validação enviado.")
	@io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Código de validação enviado para a ONG")
	@Parameter(name = "ong", description = "Usuário autenticado representando a ONG", required = true)
	public void validate(@AuthenticationPrincipal User ong, @RequestBody String validationCode) {
		this.ongService.validate(ong, validationCode);
	}
}

