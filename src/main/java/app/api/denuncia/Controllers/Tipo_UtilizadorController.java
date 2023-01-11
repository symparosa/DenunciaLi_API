package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Services.Tipo_UtilizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/tipoUtilizador", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tipo_UtilizadorController {

    private Tipo_UtilizadorService tipo_UtilizadorService;

    public Tipo_UtilizadorController(Tipo_UtilizadorService tipo_UtilizadorService) {
        this.tipo_UtilizadorService = tipo_UtilizadorService;
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Adicionar tipo de utilizador", description = "Adiciona tipo de utilizador ativo no banco de dados.", parameters = @Parameter(name = "nome", description = "O nome do tipo de utilizador"))
    @PostMapping(path = "/adicionarTipoUtilizador")
    public ResponseDto adicionarTipoUtilizador(@RequestParam(required = true) String nome) {
        return tipo_UtilizadorService.adicionarTipoUtilizador(nome);
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Listar tipo de utilizadores ativos", description = "Lista todos os tipos de utilizadores que estão ativos.")
    @GetMapping(path = "/listarTipoUtilizadoresAtivos")
    public ResponseDto listarTipoUtilizadoresAtivos() {
        return tipo_UtilizadorService.listarTipoUtilizadoresAtivos();
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Listar tipo de utilizadores inativos", description = "Lista todos os tipos de utilizadores que estão inativos.")
    @GetMapping(path = "/listarTipoUtilizadoresInativos")
    public ResponseDto listarTipoUtilizadoresInativos() {
        return tipo_UtilizadorService.listarTipoUtilizadoresInativos();
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Get tipo de utilizador by id", description = "Lista um tipo de utilizador ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de utilizador") })
    @GetMapping(path = "/getTipoUtilizadorById")
    public ResponseDto getTipoUtilizadorById(@RequestParam(required = true) int id) {
        return tipo_UtilizadorService.getTipoUtilizadorById(id);
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Desativar tipo de utilizador", description = "Desativa um tipo de utilizador ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de utilizador") })
    @PutMapping(path = "/desativarTipoUtilizador")
    public ResponseDto desativarTipoUtilizador(@RequestParam(required = true) int id) {
        return tipo_UtilizadorService.desativarTipoUtilizador(id);
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Ativar tipo de utilizador", description = "Ativa um tipo de utilizador desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de utilizador") })
    @PutMapping(path = "/ativarTipoUtilizador")
    public ResponseDto ativarTipoUtilizador(@RequestParam(required = true) int id) {
        return tipo_UtilizadorService.ativarTipoUtilizador(id);
    }

    @Operation(tags = {
            "Tipo de utilizador" }, summary = "Atualizar tipo de utilizador", description = "Atualiza os dados de um tipo de utilizador ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de utilizador"),
                    @Parameter(name = "nome", description = "O nome do tipo de utilizador")
            })
    @PutMapping(path = "/atualizarTipoUtilizador")
    public ResponseDto atualizarTipoUtilizador(@RequestParam(required = true) String nome,
            @RequestParam(required = true) int id) {
        return tipo_UtilizadorService.atualizarTipoUtilizador(nome, id);
    }
}
