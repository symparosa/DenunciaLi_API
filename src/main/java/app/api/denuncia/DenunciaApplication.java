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
				@Tag(name = "Utilizador", description = "Onde serão gerenciados os endpoits do utilizador."),
				@Tag(name = "Localização", description = "Onde serão gerenciados os endpoits da localização, exemplo: praia..."),
				@Tag(name = "Instituição de apoio", description = "Onde serão gerenciados os endpoits da instituição de apoio a vitimas de crimes, exemplo: ICIEG..."),
				@Tag(name = "Contato", description = "Onde serão gerenciados os endpoits de contatos importantes que os utilizadores poderão precisar, exemplo: Contato da polícia Nacional..."),
				@Tag(name = "Tipo de arquivo", description = "Onde serão gerenciados os endpoits de tipos de arquivos anexados pelos utlizadores, exemplo: Arquivo do tipo imagem..."),
				@Tag(name = "Tipo de queixa", description = "Onde serão gerenciados os endpoits de tipos de queixas feitas pelos utilizadores, exemplo: Queixa do tipo anónimo..."),
				@Tag(name = "Tipo de nível", description = "Onde serão gerenciados os endpoits de tipos de níveis da localização, exemplo: Nível do tipo 1 (ilha)..."),
				@Tag(name = "Tipo de utilizador", description = "Onde serão gerenciados os endpoits de tipos de utilizadores da aplicação, exemplo: Utilizador do tipo ADMIN..."),
				@Tag(name = "Tipo de crime", description = "Onde serão gerenciados os endpoits de tipos de crimes existentes, exemplo: VBG..."),
				@Tag(name = "Estatística", description = "Onde serão gerenciados os endpoits de estatística.")
		})
public class DenunciaApplication {

	public static void main(String[] args) {
		SpringApplication.run(DenunciaApplication.class, args);
	}
}
