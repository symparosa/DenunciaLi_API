package app.api.denuncia;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;

@SpringBootApplication
@OpenAPIDefinition(

		info = @Info(title = "Denuncia Li", version = "1.0.0", description = "REST API para denúncias de crimes em Cabo Verde", contact = @Contact(name = "Elisângela Rosa", email = "symparosa@gmail.com")), servers = {
				@Server(url = "http://localhost:8080"),
				@Server(url = "http://denunciaLi.com.cv")
		}, tags = {
				@Tag(name = "Denúncia", description = "Onde serão gerenciados os endpoits de denúncia."),
				@Tag(name = "Denunciante", description = "Onde serão gerenciados os endpoits do denunciante."),
				@Tag(name = "Utilizador Back-Office", description = "Onde serão gerenciados os endpoits do utilizador back-office."),
				@Tag(name = "Localização", description = "Onde serão gerenciados os endpoits da localização."),
				@Tag(name = "Entidade", description = "Onde serão gerenciados os endpoits de entidade"),
				@Tag(name = "Contato", description = "Onde serão gerenciados os endpoits de contato"),
				@Tag(name = "Domínio", description = "Onde serão gerenciados os endpoits de domínio."),
				// @Tag(name = "Estatística", description = "Onde serão gerenciados os endpoits de estatística."),
				// @Tag(name = "Estatística Por Ano", description = "Onde serão gerenciados os endpoits de estatística por ano."),
				// @Tag(name = "Estatística Por Ilha", description = "Onde serão gerenciados os endpoits de estatística por ilha."),
				// @Tag(name = "Estatística Por Concelho", description = "Onde serão gerenciados os endpoits de estatística por concelho."),
				// @Tag(name = "Estatística Por Genero", description = "Onde serão gerenciados os endpoits de estatística por genero."),
				// @Tag(name = "Estatística Por Tipo De Crime", description = "Onde serão gerenciados os endpoits de estatística por tipo de crime."),
				// @Tag(name = "Estatística Por Tipo De Queixa", description = "Onde serão gerenciados os endpoits de estatística por tipo de queixa."),
				// @Tag(name = "Estatística Por Faixa Etária", description = "Onde serão gerenciados os endpoits de estatística por faixa etária.")
		})
public class DenunciaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DenunciaApplication.class, args);
	}
}
