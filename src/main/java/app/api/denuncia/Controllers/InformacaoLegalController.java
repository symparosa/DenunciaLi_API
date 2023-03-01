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

import app.api.denuncia.Models.InformacaoLegalModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.InformacaoLegalService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Informação Legal")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/informacaoLegal", produces = MediaType.APPLICATION_JSON_VALUE)
public class InformacaoLegalController {

    private InformacaoLegalService informacaoLegalService;

    public InformacaoLegalController(InformacaoLegalService informacaoLegalService) {
        this.informacaoLegalService = informacaoLegalService;
    }

    @Operation(summary = "Adicionar / Atualizar Informação Legal", description = "Adiciona / Atualiza informação legal no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<ResponseModel> adicionar_atualizar(@RequestBody InformacaoLegalModel informacao) {
        return ResponseEntity.ok(informacaoLegalService.adicionar_atualizar(informacao));
    }

    @Operation(summary = "Alterar Estado Informação Legal", description = "Altera o estado da informação legal no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da informação legal"),
            @Parameter(name = "Estado", description = "O estado do informação legal") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<ResponseModel> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(informacaoLegalService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Informações Legais", description = "Lista todas as informações legais que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseEntity<ResponseModel> listar() {
        return ResponseEntity.ok(informacaoLegalService.listar());
    }

    @Operation(summary = "Get Informação Legal By Tipo", description = "Lista todos os dados a partir do tipo de informação legal.", parameters = {
            @Parameter(name = "TipoInfo", description = "O tipo de informação legal que se quer obter os dados") })
    @GetMapping(path = "/getInfoByTipo")
    public ResponseEntity<ResponseModel> getInfoByTipo(@RequestParam(required = true) String TipoInfo) {
        return ResponseEntity.ok(informacaoLegalService.getInfoByTipo(TipoInfo));
    }
}
