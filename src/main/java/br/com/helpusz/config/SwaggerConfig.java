package br.com.helpusz.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
	info = @Info(
		title = "Helpusz",
		version = "1.0.0",
		description = "API do projeto Helpusz"
	),
	servers = @Server(url = "http://localhost:8082")
)
public class SwaggerConfig {
}
