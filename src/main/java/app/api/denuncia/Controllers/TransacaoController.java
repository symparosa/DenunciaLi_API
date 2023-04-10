package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.TransacaoService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@Tag(name = "Transação")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/transacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(summary = "Alterar Permissão", description = "Altera acesso de um perfil ao botão no banco de dados.", parameters = {
            @Parameter(name = "IdBotao", description = "O identificador (ID) do botão"),
            @Parameter(name = "IdPerfil", description = "O identificador (ID) do perfil"),
            @Parameter(name = "Estado", description = "O estado de permissão") })
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(path = "/alterarPermissaoBotao")
    public ResponseEntity<ResponseModel> alterarPermissaoBotao(
            @RequestParam(required = true) int IdBotao,
            @RequestParam(required = true) int IdPerfil,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(transacaoService.alterarPermissao(IdBotao, IdPerfil, Estado));
    }
}
