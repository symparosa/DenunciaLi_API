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

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Services.MenuService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Menu")
@ApiResponse(responseCode = "200", description = "Success response.")
// @SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/menu", produces = MediaType.APPLICATION_JSON_VALUE)
public class MenuController {

    private MenuService menuService;

    public MenuController(MenuService menuService) {
        this.menuService = menuService;
    }

    @Operation(summary = "Adicionar / Atualizar Menu", description = "Adiciona / Atualiza menu no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody MenuModel menu) {
        return ResponseEntity.ok(menuService.adicionar_atualizar(menu));
    }

    @Operation(summary = "Alterar Estado Menu", description = "Altera o estado do menu no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do menu"),
            @Parameter(name = "Estado", description = "O estado do menu") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(menuService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Menus", description = "Lista todos os menus e os perfis associados ao mesmo.")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(menuService.listar());
    }

    @Operation(summary = "Get Detalhes Menu", description = "Lista todos os detalhes do menu.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do menu") })
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(menuService.get_by_id(Id));
    }
}
