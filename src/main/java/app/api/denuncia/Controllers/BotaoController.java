package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Services.BotaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/botao", produces = MediaType.APPLICATION_JSON_VALUE)
public class BotaoController {

    private BotaoService botaoService;

    public BotaoController(BotaoService botaoService) {
        this.botaoService = botaoService;
    }

    @Operation(tags = {
            "Botão" }, summary = "Adicionar / Atualizar Botão", description = "Adiciona / Atualiza botão no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseDto adicionar_atualizar(@RequestBody BotaoModel botao) {
        return botaoService.adicionar_atualizar(botao);
    }

    @Operation(tags = {
            "Botão" }, summary = "Alterar Estado Botão", description = "Altera o estado do botão no banco de dados.", parameters = {
                    @Parameter(name = "Id", description = "O identificador (ID) do botão"),
                    @Parameter(name = "Estado", description = "O estado do botão") })
    @PutMapping(path = "/alterarEstado")
    public ResponseDto alterarEstado(@RequestParam(required = true) int Id, @RequestParam(required = true) int Estado) {
        return botaoService.alterarEstado(Id, Estado);
    }

    @Operation(tags = {
            "Botão" }, summary = "Listar Botões", description = "Lista todos os botões que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseDto listar() {
        return botaoService.listar();
    }
}
