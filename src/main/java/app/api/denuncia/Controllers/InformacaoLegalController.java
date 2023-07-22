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
import app.api.denuncia.Models.InformacaoLegalModel;
import app.api.denuncia.Services.InformacaoLegalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Informação Legal")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/informacaoLegal", produces = MediaType.APPLICATION_JSON_VALUE)
public class InformacaoLegalController {

    private InformacaoLegalService info;

    public InformacaoLegalController(InformacaoLegalService info) {
        this.info = info;
    }

    @Operation(summary = "Adicionar / Atualizar Informação Legal", description = "Adiciona / Atualiza informação legal.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody InformacaoLegalModel informacao) {
        return ResponseEntity.ok(info.adicionar_atualizar(informacao));
    }

    @Operation(summary = "Alterar Estado Informação Legal", description = "Altera o estado de uma informação legal.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da informação legal"),
            @Parameter(name = "Estado", description = "O estado do informação legal") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(info.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Informações Legais", description = "Lista todas as informações legais.")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(info.listar());
    }

    @Operation(summary = "Get Informação Legal By Tipo", description = "Lista todos os dados a partir de um tipo de informação legal.", parameters = {
            @Parameter(name = "TipoInfo", description = "O tipo de informação legal que se quer obter os dados") })
    @GetMapping(path = "/getInfoByTipo")
    public ResponseEntity<Response> getInfoByTipo(@RequestParam(required = true) String TipoInfo) {
        return ResponseEntity.ok(info.getInfoByTipo(TipoInfo));
    }

    @Operation(summary = "Get Detalhes Informação Legal", description = "Lista todos os detalhes de uma informação legal.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da informação legal") })
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(info.get_by_id(Id));
    }
}
