package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Services.UtilizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Utilizador Back-Office")
@ApiResponse(responseCode = "200", description = "Success response.")
@RequestMapping(path = "/api/utilizadorBackoffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilizadorController {

    private UtilizadorService user;

    public UtilizadorController(UtilizadorService user) {
        this.user = user;
    }

    @Operation(summary = "Adicionar / Atualizar Utilizador", description = "Adiciona / Atualiza utilizador no banco de dados.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody UtilizadorModel utilizador) {
        return ResponseEntity.ok(user.adicionar_atualizar(utilizador));
    }

    @Operation(summary = "Alterar Estado Utilizador", description = "Altera o estado do utilizador no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do utilizador"),
            @Parameter(name = "Estado", description = "O estado do utilizador") })
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(user.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Utilizadores", description = "Lista todos os utilizadores que estão no banco de dados.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(user.listar());
    }

    @Operation(summary = "Alterar Password", description = "Altera o password do utilizador no banco de dados.", parameters = {
            @Parameter(name = "Hash", description = "O hash do utilizador"),
            @Parameter(name = "Password", description = "O novo password do utilizador"),
            @Parameter(name = "Username", description = "O username do utilizador") })
    @PutMapping(path = "/alterarPassword")
    public ResponseEntity<Response> alterarPassword(
            @RequestParam(required = true) String Hash,
            @RequestParam(required = true) String Password,
            @RequestParam(required = true) String Username) {
        return ResponseEntity.ok(user.alterarPassword(Username, Hash, Password));
    }

    @Operation(summary = "Recuperar Conta", description = "Envia hash para o email.", parameters = {
            @Parameter(name = "Email", description = "O email para onde o hash será enviado") })
    @PostMapping(path = "/recuperarConta")
    public ResponseEntity<Response> recuperarConta(@RequestParam(required = true) String Email) {
        return ResponseEntity.ok(user.recuperarConta(Email));
    }

    @Operation(summary = "Get Detalhes Utilizador", description = "Lista todos os detalhes do utilizador.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do utilizador") })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(user.get_by_id(Id));
    }
}
