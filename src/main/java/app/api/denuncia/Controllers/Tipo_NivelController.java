package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Services.Tipo_NivelService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/tipoNivel", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tipo_NivelController {

    private Tipo_NivelService tipo_NivelService;

    public Tipo_NivelController(Tipo_NivelService tipo_NivelService) {
        this.tipo_NivelService = tipo_NivelService;
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Adicionar tipo de nível", description = "Adiciona tipo de nível ativo no banco de dados.", parameters = {
                    @Parameter(name = "nome", description = "O nome do tipo de nível") })
    @PostMapping(path = "/adicionarTipoNivel")
    public ResponseDto adicionarTipoNivel(@RequestParam(required = true) String nome) {
        return tipo_NivelService.adicionarTipoNivel(nome);
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Listar tipo de níveis ativos", description = "Lista todos os tipos de níveis que estão ativos.")
    @GetMapping(path = "/listarTipoNiveisAtivos")
    public ResponseDto listarTipoNiveisAtivos() {
        return tipo_NivelService.listarTipoNiveisAtivos();
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Listar tipo de níveis inativos", description = "Lista todos os tipos de níveis que estão inativos.")
    @GetMapping(path = "/listarTipoNiveisInativos")
    public ResponseDto listarTipoNiveisInativos() {
        return tipo_NivelService.listarTipoNiveisInativos();
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Get tipo de nível by id", description = "Lista um tipo de nível ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de nível") })
    @GetMapping(path = "/getTipoNivelById")
    public ResponseDto getTipoNivelById(@RequestParam(required = true) int id) {
        return tipo_NivelService.getTipoNivelById(id);
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Desativar tipo de nível", description = "Desativa um tipo de nível ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de nível") })
    @PutMapping(path = "/desativarTipoNivel")
    public ResponseDto desativarTipoNivel(@RequestParam(required = true) int id) {
        return tipo_NivelService.desativarTipoNivel(id);
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Ativar tipo de nível", description = "Ativa um tipo de nível desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de nível") })
    @PutMapping(path = "/ativarTipoNivel")
    public ResponseDto ativarTipoNivel(@RequestParam(required = true) int id) {
        return tipo_NivelService.ativarTipoNivel(id);
    }

    @Operation(tags = {
            "Tipo de nível" }, summary = "Atualizar tipo de nível", description = "Atualiza os dados de um tipo de nível ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de nível"),
                    @Parameter(name = "nome", description = "O nome do tipo de nível")
            })
    @PutMapping(path = "/atualizarTipoNivel")
    public ResponseDto atualizarTipoNivel(@RequestParam(required = true) String nome,
            @RequestParam(required = true) int id) {
        return tipo_NivelService.atualizarTipoNivel(nome, id);
    }
}
