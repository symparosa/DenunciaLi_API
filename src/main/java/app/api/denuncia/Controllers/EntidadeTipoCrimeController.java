package app.api.denuncia.Controllers;

import java.util.List;

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
import app.api.denuncia.Models.EntidadeTipoCrimeModel;
import app.api.denuncia.Services.EntidadeTipoCrimeService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Tag(name = "Entidade Tipo Crime")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/entidadeTipoCrime", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeTipoCrimeController {

    private EntidadeTipoCrimeService entidadeTipoCrimeService;

    public EntidadeTipoCrimeController(EntidadeTipoCrimeService entidadeTipoCrimeService) {
        this.entidadeTipoCrimeService = entidadeTipoCrimeService;
    }

    @Operation(summary = "Adicionar / Atualizar Entidade Tipo Crime", description = "Adiciona / Atualiza entidade tipo de crime.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(
            @RequestBody List<EntidadeTipoCrimeModel> entidadeTipoCrime) {
        return ResponseEntity.ok(entidadeTipoCrimeService.adicionar_atualizar(entidadeTipoCrime));
    }

    @Operation(summary = "Alterar Estado Entidade Tipo Crime", description = "Altera o estado da entidade tipo de crime.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da entidade tipo de crime"),
            @Parameter(name = "Estado", description = "O estado da entidade tipo de crime") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(entidadeTipoCrimeService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Get Info By Entidade", description = "Lista todos os dados a partir da entidade.", parameters = {
            @Parameter(name = "IdEntidade", description = "O identificador (ID) da entidade") })
    @GetMapping(path = "/getInfoByEntidade")
    public ResponseEntity<Response> getInfoByEntidade(@RequestParam(required = true) int IdEntidade) {
        return ResponseEntity.ok(entidadeTipoCrimeService.getInfoByEntidade(IdEntidade));
    }
}
