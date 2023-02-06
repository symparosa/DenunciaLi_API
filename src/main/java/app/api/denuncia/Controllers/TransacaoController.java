package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.TransacaoService;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/transacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class TransacaoController {

    private TransacaoService transacaoService;

    public TransacaoController(TransacaoService transacaoService) {
        this.transacaoService = transacaoService;
    }

    @Operation(tags = {
            "Transação" }, summary = "Alterar Permissão", description = "Altera acesso de um perfil ao botão no banco de dados.", parameters = {
                    @Parameter(name = "IdBotao", description = "O identificador (ID) do botão"),
                    @Parameter(name = "IdPerfil", description = "O identificador (ID) do perfil"),
                    @Parameter(name = "Estado", description = "O estado de permissão") })
    @PutMapping(path = "/alterarPermissao")
    public ResponseModel alterarPermissao(
            @RequestParam(required = true) int IdBotao,
            @RequestParam(required = true) int IdPerfil,
            @RequestParam(required = true) int Estado) {
        return transacaoService.alterarPermissao(IdBotao, IdPerfil, Estado);
    }
}
