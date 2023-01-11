package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Services.Tipo_QueixaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/tipoQueixa", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tipo_QueixaController {

    private Tipo_QueixaService Tipo_QueixaService;

    public Tipo_QueixaController(Tipo_QueixaService tipo_QueixaService) {
        Tipo_QueixaService = tipo_QueixaService;
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Adicionar tipo de queixa", description = "Adiciona tipo de queixa ativo no banco de dados.", parameters = {
                    @Parameter(name = "nome", description = "O nome do tipo de queixa") })
    @PostMapping(path = "/adicionarTipoQueixa")
    public ResponseDto adicionarTipoQueixa(@RequestParam(required = true) String nome) {
        return Tipo_QueixaService.adicionarTipoQueixa(nome);
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Listar tipo de queixa ativos", description = "Lista todos os tipos de queixas que estão ativos.")
    @GetMapping(path = "/listarTipoQueixasAtivas")
    public ResponseDto listarTipoQueixasAtivas() {
        return Tipo_QueixaService.listarTipoQueixasAtivas();
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Listar tipo de queixa inativos", description = "Lista todos os tipos de queixas que estão inativos.")
    @GetMapping(path = "/listarTipoQueixasInativas")
    public ResponseDto listarTipoQueixasInativas() {
        return Tipo_QueixaService.listarTipoQueixasInativas();
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Get tipo de queixa by id", description = "Lista um tipo de queixa ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de queixa") })
    @GetMapping(path = "/getTipoQueixaById")
    public ResponseDto getTipoQueixaById(@RequestParam(required = true) int id) {
        return Tipo_QueixaService.getTipoQueixaById(id);
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Desativar tipo de queixa", description = "Desativa um tipo de queixa ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de queixa") })
    @PutMapping(path = "/desativarTipoQueixa")
    public ResponseDto desativarTipoQueixa(@RequestParam(required = true) int id) {
        return Tipo_QueixaService.desativarTipoQueixa(id);
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Ativar tipo de queixa", description = "Ativa um tipo de queixa desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de queixa") })
    @PutMapping(path = "/ativarTipoQueixa")
    public ResponseDto ativarTipoQueixa(@RequestParam(required = true) int id) {
        return Tipo_QueixaService.ativarTipoQueixa(id);
    }

    @Operation(tags = {
            "Tipo de queixa" }, summary = "Atualizar tipo de queixa", description = "Atualiza os dados de um tipo de queixa ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de queixa"),
                    @Parameter(name = "nome", description = "O nome do tipo de queixa")
            })
    @PutMapping(path = "/atualizarTipoQueixa")
    public ResponseDto atualizarTipoQueixa(@RequestParam(required = true) int id,
            @RequestParam(required = true) String nome) {
        return Tipo_QueixaService.atualizarTipoQueixa(nome, id);
    }
}
