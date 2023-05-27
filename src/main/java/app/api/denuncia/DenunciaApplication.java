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
				@Tag(name = "Autenticação", description = "Operações relacionadas à autenticação e autorização na API."),
				@Tag(name = "Denúncia", description = "Onde serão gerenciados os endpoits de denúncia."),
				@Tag(name = "Denunciante", description = "Operações relacionadas à gestão de denunciantes na API."),
				@Tag(name = "Utilizador Back-Office", description = "Operações relacionadas à gestão de utilizadores backoffice na API."),
				@Tag(name = "Localização", description = "Operações relacionadas à gestão de localizações na API."),
				@Tag(name = "Domínio", description = "Operações relacionadas à gestão de dominios na API."),
				@Tag(name = "Menu", description = "Operações relacionadas à gestão de menus na API."),
				@Tag(name = "Menu Perfil", description = "Operações relacionadas à gestão de menus e perfis na API."),
				@Tag(name = "Botão", description = "Operações relacionadas à gestão de botões na API."),
				@Tag(name = "Transação", description = "Operações relacionadas à gestão de transações na API."),
				@Tag(name = "Banner", description = "Operações relacionadas à gestão de banners na API."),
				@Tag(name = "Entidade", description = "Operações relacionadas à gestão de entidades na API."),
				@Tag(name = "Entidade Tipo Crime", description = "Operações relacionadas à gestão de entidades e tipos de crime na API."),
				@Tag(name = "Contato", description = "Operações relacionadas à gestão de contatos na API."),
				@Tag(name = "Informação Legal", description = "Operações relacionadas à gestão de informações legais na API."),
				@Tag(name = "AES256", description = "Operações relacionadas à gestão de AES256 na API."),
				@Tag(name = "Estatística Denúncia", description = "Operações relacionadas à gestão de estatística de denúncias na API."),
				@Tag(name = "Estatística Denúncia - Por Ano", description = "Operações relacionadas à gestão de estatística de denúncias por anos na API."),
				@Tag(name = "Estatística Denúncia - Por Ano e Mês", description = "Operações relacionadas à gestão de estatística de denúncias por anos e meses na API."),
				@Tag(name = "Estatística Denúncia - Por Ano e Tipo de Crime", description = "Operações relacionadas à gestão de estatística de denúncias por anos e tipos de crimes na API."),
				@Tag(name = "Estatística Denúncia - Por Ano e Faixa Etária", description = "Operações relacionadas à gestão de estatística de denúncias por anos e faixas etárias na API."),
				@Tag(name = "Estatística Denúncia - Por Ano e Ilha", description = "Operações relacionadas à gestão de estatística de denúncias por anos e ilhas na API."),
				@Tag(name = "Estatística Denúncia - Por Ano e Concelho", description = "Operações relacionadas à gestão de estatística de denúncias por anos e concelhos na API."),
				@Tag(name = "Estatística Denúncia - Por Ilha", description = "Operações relacionadas à gestão de estatística de denúncias por ilhas na API."),
				@Tag(name = "Estatística Denúncia - Por Concelho", description = "Operações relacionadas à gestão de estatística de denúncias por concelhos na API."),
				@Tag(name = "Estatística Denúncia - Por genero", description = "Operações relacionadas à gestão de estatística de denúncias por generos na API."),
				@Tag(name = "Estatística Denúncia - Por tipo de crime", description = "Operações relacionadas à gestão de estatística de denúncias por tipos de crimes na API."),
				@Tag(name = "Estatística Denúncia - Por tipo de queixa", description = "Operações relacionadas à gestão de estatística de denúncias por tipos de queixas na API."),
				@Tag(name = "Estatística Denúncia - Por faixa etária", description = "Operações relacionadas à gestão de estatística de denúncias por faixas etárias na API."),
				@Tag(name = "Estatística Utilizador", description = "Operações relacionadas à gestão de estatística de utilizadores na API.")
		})
@SecurityScheme(name = "Bearer Authentication", type = SecuritySchemeType.HTTP, bearerFormat = "JWT", scheme = "bearer", description = "A JWT token is required to access this API...")
public class DenunciaApplication {
	public static void main(String[] args) {
		SpringApplication.run(DenunciaApplication.class, args);
	}
}
