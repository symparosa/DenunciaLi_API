package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Services.MenuPerfilService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/menuPerfil", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuPerfilController {

    private MenuPerfilService menuPerfilService;

    public MenuPerfilController(MenuPerfilService menuPerfilService) {
        this.menuPerfilService = menuPerfilService;
    }

    @Operation(tags = {
            "Menu Perfil" }, summary = "Alterar Permissão", description = "Altera acesso de um perfil ao menu no banco de dados.", parameters = {
                    @Parameter(name = "IdMenu", description = "O identificador (ID) do menu"),
                    @Parameter(name = "IdPerfil", description = "O identificador (ID) do perfil"),
                    @Parameter(name = "Estado", description = "O estado de permissão") })
    @PutMapping(path = "/alterarPermissao")
    public ResponseDto alterarPermissao(
            @RequestParam(required = true) int IdMenu,
            @RequestParam(required = true) int IdPerfil,
            @RequestParam(required = true) int Estado) {
        return menuPerfilService.alterarPermissao(IdMenu, IdPerfil, Estado);
    }
}
