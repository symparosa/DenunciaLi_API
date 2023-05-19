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
import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Services.BotaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Botão")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/botao", produces = MediaType.APPLICATION_JSON_VALUE)
public class BotaoController {

    private BotaoService botaoService;

    public BotaoController(BotaoService botaoService) {
        this.botaoService = botaoService;
    }

    @Operation(summary = "Adicionar / Atualizar Botão", description = "Adiciona / Atualiza botão no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody BotaoModel botao) {
        return ResponseEntity.ok(botaoService.adicionar_atualizar(botao));
    }

    @Operation(summary = "Alterar Estado Botão", description = "Altera o estado do botão no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do botão"),
            @Parameter(name = "Estado", description = "O estado do botão") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(botaoService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Botões", description = "Lista todos os botões que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(botaoService.listar());
    }

    @Operation(summary = "Get Detalhes Botão", description = "Lista todos os detalhes do botão.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do botão") })
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(botaoService.get_by_id(Id));
    }
}
