package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.ContatoInputInsertDto;
import app.api.denuncia.Dto.ContatoInputUpdateDto;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/contato", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContatoController {

    private ContatoService ContatoService;

    public ContatoController(ContatoService contatoService) {
        ContatoService = contatoService;
    }

    @Operation(tags = {
            "Contato" }, summary = "Adicionar contato", description = "Adiciona contato ativo no banco de dados.")
    @PostMapping(path = "/adicionarContato")
    public ResponseDto adicionarContato(@RequestBody ContatoInputInsertDto contatoDto) {
        return ContatoService.adicionarContato(contatoDto.getTelefone(), contatoDto.getLogotipo(),
                contatoDto.getNome());
    }

    @Operation(tags = {
            "Contato" }, summary = "Listar contatos ativos", description = "Lista todos os contatos que estão ativos.")
    @GetMapping(path = "/listarContatoAtivos")
    public ResponseDto listarContatoAtivos() {
        return ContatoService.listarContatoAtivos();
    }

    @Operation(tags = {
            "Contato" }, summary = "Listar contatos inativos", description = "Lista todos os contatos que estão inativos.")
    @GetMapping(path = "/listarContatoInativos")
    public ResponseDto listarContatoInativos() {
        return ContatoService.listarContatoInativos();
    }

    @Operation(tags = {
            "Contato" }, summary = "Get contato by id", description = "Lista um contato ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do contato") })
    @GetMapping(path = "/getContatoById")
    public ResponseDto getContatoById(@RequestParam(required = true) int id) {
        return ContatoService.getContatoById(id);
    }

    @Operation(tags = {
            "Contato" }, summary = "Desativar contato", description = "Desativa um contato ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do contato") })
    @PutMapping(path = "/desativarContato")
    public ResponseDto desativarContato(@RequestParam(required = true) int id) {
        return ContatoService.desativarContato(id);
    }

    @Operation(tags = {
            "Contato" }, summary = "Ativar contato", description = "Ativa um contato desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do contato") })
    @PutMapping(path = "/ativarContato")
    public ResponseDto ativarContato(@RequestParam(required = true) int id) {
        return ContatoService.ativarContato(id);
    }

    @Operation(tags = {
            "Contato" }, summary = "Atualizar informação de contato", description = "Atualiza os dados de um contato ativo a partir do ID.")
    @PutMapping(path = "/atualizarContatoInfo")
    public ResponseDto atualizarContatoInfo(@RequestBody ContatoInputUpdateDto contatoDto) {
        return ContatoService.atualizarContatoInfo(contatoDto.getTelefone(), contatoDto.getNome(),
                contatoDto.getLogotipo(), contatoDto.getId());
    }
}
