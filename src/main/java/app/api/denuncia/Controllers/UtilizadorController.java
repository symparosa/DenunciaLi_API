package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.UtilizadorInputInsertDto;
import app.api.denuncia.Dto.UtilizadorInputUpdateDto;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Services.UtilizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/utilizador", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilizadorController {

    private UtilizadorService UtilizadorService;

    public UtilizadorController(app.api.denuncia.Services.UtilizadorService utilizadorService) {
        UtilizadorService = utilizadorService;
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Adicionar utilizador", description = "Adiciona utilizador ativo no banco de dados.")
    @PostMapping(path = "/adicionarUtilizador")
    public ResponseDto adicionarUtilizador(@RequestBody UtilizadorInputInsertDto user){

        String cni = null;
        String bi = null;

        if (user.getNumeroDeIdentificacao().matches("[0-9]+")) {
            bi = user.getNumeroDeIdentificacao();
        } else {
            cni = user.getNumeroDeIdentificacao();
        }

        return UtilizadorService.adicionarUtilizador(user.getApelido(), bi, cni, user.getDataNascimento(),
                user.getEmail(), user.getFoto(), user.getGenero(), user.getNome(), user.getTelemovel(),
                user.getLocalizacao(), user.getUsername(), user.getTipoUtilizador());
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Listar utilizador ativos", description = "Lista todos os utilizadores que estão ativos.")
    @GetMapping(path = "/listarUtilizadorsAtivos")
    public ResponseDto listarUtilizadorsAtivos() {
        return UtilizadorService.listarUtilizadoresAtivos();
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Listar utilizador inativos", description = "Lista todos os utilizadores que estão inativos.")
    @GetMapping(path = "/listarUtilizadorsInativos")
    public ResponseDto listarUtilizadorsInativos() {
        return UtilizadorService.listarUtilizadoresInativos();
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Get utilizador by id", description = "Lista um utilizador ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do utilizador") })
    @GetMapping(path = "/getUtilizadorById")
    public ResponseDto getUtilizadorById(@RequestParam(required = true) int id) {
        return UtilizadorService.getUtilizadorById(id);
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Desativar utilizador", description = "Desativa um utilizador ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do utilizador") })
    @PutMapping(path = "/desativarUtilizador")
    public ResponseDto desativarUtilizador(@RequestParam(required = true) int id) {
        return UtilizadorService.desativarUtilizador(id);
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Ativar utilizador", description = "Ativa um utilizador desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do utilizador") })
    @PutMapping(path = "/ativarUtilizador")
    public ResponseDto ativarUtilizador(@RequestParam(required = true) int id) {
        return UtilizadorService.ativarUtilizador(id);
    }

    @Operation(tags = {
            "Utilizador" }, summary = "Atualizar informação do utilizador", description = "Atualiza os dados de um utilizador ativo a partir do ID.")
    @PutMapping(path = "/atualizarUtilizadorInfo")
    public ResponseDto atualizarUtilizadorInfo(@RequestBody UtilizadorInputUpdateDto user){
        return UtilizadorService.atualizarUtilizadorInfo(user.getApelido(), user.getEmail(), user.getNome(), user.getTelemovel(), user.getLocalizacao(),user.getFoto(), user.getId());
    }
}
