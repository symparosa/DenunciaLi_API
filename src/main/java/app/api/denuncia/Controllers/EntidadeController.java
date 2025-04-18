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
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Services.EntidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Entidade")
@ApiResponse(responseCode = "200", description = "Success response.")
@RequestMapping(path = "/api/entidade", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {

    private EntidadeService entidadeService;

    public EntidadeController(EntidadeService entidadeService) {
        this.entidadeService = entidadeService;
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Adicionar / Atualizar Entidade", description = "Adiciona / Atualiza entidade.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody EntidadeModel entidade) {
        return ResponseEntity.ok(entidadeService.adicionar_atualizar(entidade));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Alterar Estado Entidade", description = "Altera o estado de uma entidade.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da entidade"),
            @Parameter(name = "Estado", description = "O estado da entidade") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(entidadeService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Entidades", description = "Lista todas as entidades.")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(entidadeService.listar());
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Detalhes Entidade", description = "Lista todos os detalhes de uma entidade.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da entidade") })
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(entidadeService.get_by_id(Id));
    }

    @SecurityRequirement(name = "Bearer Authentication")
    @Operation(summary = "Get Entidade By Tipo", description = "Lista todos os dados a partir de um tipo de entidade.", parameters = {
            @Parameter(name = "TipoEntidade", description = "O tipo de entidade que se quer obter os dados") })
    @GetMapping(path = "/getEntidadeByTipo")
    public ResponseEntity<Response> getEntidadeByTipo(@RequestParam(required = true) String TipoEntidade) {
        return ResponseEntity.ok(entidadeService.getEntidadeByTipo(TipoEntidade));
    }
}
