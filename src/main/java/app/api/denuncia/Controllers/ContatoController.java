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
import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Contato")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/contato", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContatoController {

    private ContatoService ContatoService;

    public ContatoController(ContatoService contatoService) {
        ContatoService = contatoService;
    }

    @Operation(summary = "Adicionar / Atualizar Contato", description = "Adiciona / Atualiza contato.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody List<ContatoModel> contato) {
        return ResponseEntity.ok(ContatoService.adicionar_atualizar(contato));
    }

    @Operation(summary = "Alterar Estado Contato", description = "Altera o estado de um contato.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do contato"),
            @Parameter(name = "Estado", description = "O estado do contato") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(ContatoService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Get Info By IdObjeto e TipoObjeto", description = "Lista todos os dados a partir do id objeto e do tipo de objeto.", parameters = {
            @Parameter(name = "IdObjeto", description = "O id objeto que se quer obter dados"),
            @Parameter(name = "TipoObjeto", description = "O tipo objeto que se quer obter dados") })
    @GetMapping(path = "/getInfoByIdObjeto")
    public ResponseEntity<Response> getInfoByIdObjeto(
            @RequestParam(required = true) int IdObjeto,
            @RequestParam(required = true) String TipoObjeto) {
        return ResponseEntity.ok(ContatoService.getInfoByIdObjeto(IdObjeto, TipoObjeto));
    }
}
