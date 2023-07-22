package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Services.MenuPerfilService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Tag(name = "Menu Perfil")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/menuPerfil", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuPerfilController {

    private MenuPerfilService menuPerfilService;

    public MenuPerfilController(MenuPerfilService menuPerfilService) {
        this.menuPerfilService = menuPerfilService;
    }

    @Operation(summary = "Alterar Permissão", description = "Altera acesso de um perfil ao menu no banco de dados.", parameters = {
            @Parameter(name = "IdMenu", description = "O identificador (ID) do menu"),
            @Parameter(name = "IdPerfil", description = "O identificador (ID) do perfil"),
            @Parameter(name = "Estado", description = "O estado de permissão") })
    // @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/alterarPermissaoMenu")
    public ResponseEntity<Response> alterarPermissaoMenu(
            @RequestParam(required = true) int IdMenu,
            @RequestParam(required = true) int IdPerfil,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(menuPerfilService.alterarPermissao(IdMenu, IdPerfil, Estado));
    }
}
