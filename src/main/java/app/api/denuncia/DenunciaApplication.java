package app.api.denuncia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@Configuration
@OpenAPIDefinition(

		info = @Info(title = "Denuncia Li", version = "1.0.0", description = "REST API para denúncias de crimes em Cabo Verde", contact = @Contact(name = "Elisângela Rosa", email = "symparosa@gmail.com")), servers = {
				@Server(url = "http://localhost:8080"),
				@Server(url = "http://denunciaLi.com.cv")
		}, tags = {
				@Tag(name = "Autenticação", description = "Onde serão gerenciados os endpoits de autenticação."),
				// @Tag(name = "Denúncia", description = "Onde serão gerenciados os endpoits de
				// denúncia."),
				// @Tag(name = "Denunciante", description = "Onde serão gerenciados os endpoits
				// do denunciante."),
				@Tag(name = "Utilizador Back-Office", description = "Onde serão gerenciados os endpoits do utilizador back-office."),
				// @Tag(name = "Localização", description = "Onde serão gerenciados os endpoits
				// da localização."),
				@Tag(name = "Domínio", description = "Onde serão gerenciados os endpoits do domínio."),
				@Tag(name = "Menu", description = "Onde serão gerenciados os endpoits do menu."),
				@Tag(name = "Menu Perfil", description = "Onde serão gerenciados os endpoits do menu perfil."),
				@Tag(name = "Botão", description = "Onde serão gerenciados os endpoits do botão."),
				@Tag(name = "Transação", description = "Onde serão gerenciados os endpoits de transação."),
				@Tag(name = "Banner", description = "Onde serão gerenciados os endpoits de banner."),
				@Tag(name = "Entidade", description = "Onde serão gerenciados os endpoits de entidade."),
				@Tag(name = "Entidade Tipo Crime", description = "Onde serão gerenciados os endpoits de entidade tipo crime."),
				@Tag(name = "Contato", description = "Onde serão gerenciados os endpoits do contato."),
				@Tag(name = "Informação Legal", description = "Onde serão gerenciados os endpoits de informação legal.")
		// @Tag(name = "Estatística Por Ano", description = "Onde serão gerenciados os
		// endpoits de estatística por ano."),
		// @Tag(name = "Estatística Por Ilha", description = "Onde serão gerenciados os
		// endpoits de estatística por ilha."),
		// @Tag(name = "Estatística Por Concelho", description = "Onde serão gerenciados
		// os endpoits de estatística por concelho."),
		// @Tag(name = "Estatística Por Genero", description = "Onde serão gerenciados
		// os endpoits de estatística por genero."),
		// @Tag(name = "Estatística Por Tipo De Crime", description = "Onde serão
		// gerenciados os endpoits de estatística por tipo de crime."),
		// @Tag(name = "Estatística Por Tipo De Queixa", description = "Onde serão
		// gerenciados os endpoits de estatística por tipo de queixa."),
		// @Tag(name = "Estatística Por Faixa Etária", description = "Onde serão
		// gerenciados os endpoits de estatística por faixa etária.")
		})
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", description = "A JWT token is required to access this API...")
public class DenunciaApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(DenunciaApplication.class, args);
	}
}
