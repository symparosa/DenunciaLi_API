package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.DenuncianteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Tag(name = "Denunciante")
@ApiResponse(responseCode = "200", description = "Success response.")
@RequestMapping(path = "/api/denunciante", produces = MediaType.APPLICATION_JSON_VALUE)
public class DenuncianteController {

    private DenuncianteService denuncianteService;

    public DenuncianteController(DenuncianteService denuncianteService) {
        this.denuncianteService = denuncianteService;
    }

    @Operation(summary = "Adicionar Denunciante", description = "Adiciona denunciante no banco de dados.", parameters = {
            @Parameter(name = "Username", description = "O username do denunciante", example = "MariaFontes@gmail.com"),
            @Parameter(name = "Nome", description = "O nome do denunciante", example = "Maria") })
    @PostMapping(path = "/adicionar")
    public ResponseEntity<ResponseModel> adicionar(
            @RequestParam(required = true) String Username,
            @RequestParam(required = true) String Nome) {
        return ResponseEntity.ok(denuncianteService.adicionar(Username, Nome));
    }

    @Operation(summary = "Atualizar Denunciante", description = "Atualizar denunciante no banco de dados.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(path = "/atualizar")
    public ResponseEntity<ResponseModel> atualizar(
            @RequestBody DenuncianteModel denunciante) {
        return ResponseEntity.ok(denuncianteService.atualizar(denunciante));
    }

    @Operation(summary = "Alterar Password", description = "Altera o password do denunciante no banco de dados.", parameters = {
            @Parameter(name = "Password", description = "O novo password do denunciante", example = "yETSiJYQU0lU+vY2HUcvWg=="),
            @Parameter(name = "Username", description = "O username do denunciante", example = "MariaFontes@gmail.com") })
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(path = "/alterarPassword")
    public ResponseEntity<ResponseModel> alterarPassword(
            @RequestParam(required = true) String Password,
            @RequestParam(required = true) String Username) {
        return ResponseEntity.ok(denuncianteService.alterarPassword(Username, null, Password, true));
    }

    @Operation(summary = "Recuperar Senha", description = "Recuperar o password do denunciante no banco de dados.", parameters = {
            @Parameter(name = "Hash", description = "O hash do denunciante"),
            @Parameter(name = "Password", description = "O novo password do denunciante", example = "yETSiJYQU0lU+vY2HUcvWg=="),
            @Parameter(name = "Username", description = "O username do denunciante", example = "MariaFontes@gmail.com") })
    @PutMapping(path = "/recuperarSenha")
    public ResponseEntity<ResponseModel> recuperarSenha(
            @RequestParam(required = true) String Hash,
            @RequestParam(required = true) String Password,
            @RequestParam(required = true) String Username) {
        return ResponseEntity.ok(denuncianteService.alterarPassword(Username, Hash, Password, false));
    }

    @Operation(summary = "Eliminar Conta", description = "Elimina a conta do denunciante no banco de dados.")
    @SecurityRequirement(name = "Bearer Authentication")
    @PutMapping(path = "/eliminarConta")
    public ResponseEntity<ResponseModel> eliminarConta() {
        return ResponseEntity.ok(denuncianteService.eliminarConta(-1));
    }

    @Operation(summary = "Get Detalhes Denunciante", description = "Lista todos os detalhes do denunciante.", parameters = {
            @Parameter(name = "Username", description = "O Username do denunciante", example = "MariaFontes@gmail.com") })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/get_detalhes_by_username")
    public ResponseEntity<ResponseModel> get_detalhes_by_username(
            @RequestParam(required = true) String Username) {
        return ResponseEntity.ok(denuncianteService.get_by_username(Username));
    }

    @Operation(summary = "Recuperar Conta", description = "Envia hash para o email.", parameters = {
            @Parameter(name = "Email", description = "O email para onde o hash será enviado", example = "MariaFontes@gmail.com") })
    @PostMapping(path = "/recuperarConta")
    public ResponseEntity<ResponseModel> recuperarConta(
            @RequestParam(required = true) String Email) {
        return ResponseEntity.ok(denuncianteService.recuperarConta(Email));
    }

    @Operation(summary = "Validar Senha", description = "Valida senha de denunciante.", parameters = {
            @Parameter(name = "Senha", description = "A senha do denunciante", example = "yETSiJYQU0lU+vY2HUcvWg==") })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping(path = "/validarSenhaAtual")
    public ResponseEntity<ResponseModel> validarSenhaAtual(
            @RequestParam(required = true) String Senha) {
        return ResponseEntity.ok(denuncianteService.validarSenhaAtual(Senha));
    }
}
