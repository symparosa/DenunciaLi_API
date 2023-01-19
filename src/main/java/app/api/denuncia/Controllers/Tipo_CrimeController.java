package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Tipo_CrimeInputInsertDto;
import app.api.denuncia.Dto.Tipo_CrimeInputUpdateDto;
import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Services.Tipo_CrimeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/tipoCrime", produces = MediaType.APPLICATION_JSON_VALUE)
public class Tipo_CrimeController {

    private Tipo_CrimeService Tipo_CrimeService;

    public Tipo_CrimeController(Tipo_CrimeService tipo_CrimeService) {
        Tipo_CrimeService = tipo_CrimeService;
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Adicionar tipo de crime", description = "Adiciona tipo de nível ativo no banco de dados.")
    @PostMapping(path = "/adicionarTipoCrime")
    public ResponseDto adicionarTipoCrime(@RequestBody Tipo_CrimeInputInsertDto tipoCrime){
        return Tipo_CrimeService.adicionarTipoCrime(tipoCrime.getNome(), tipoCrime.getLogotipo());
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Listar tipo de crimes ativos", description = "Lista todos os tipos de crimes que estão ativos.")
    @GetMapping(path = "/listarTipoCrimesAtivos")
    public ResponseDto listarTipoCrimesAtivos() {
        return Tipo_CrimeService.listarTipoCrimesAtivos();
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Listar tipo de crimes inativos", description = "Lista todos os tipos de crimes que estão inativos.")
    @GetMapping(path = "/listarTipoCrimesInativos")
    public ResponseDto listarTipoCrimesInativos() {
        return Tipo_CrimeService.listarTipoCrimesInativos();
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Get tipo de crime by id", description = "Lista um tipo de crime ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de crime") })
    @GetMapping(path = "/getTipoCrimeById")
    public ResponseDto getTipoCrimeById(@RequestParam(required = true) int id) {
        return Tipo_CrimeService.getTipoCrimeById(id);
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Desativar tipo de crime", description = "Desativa um tipo de crime ativo a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de crime") })
    @PutMapping(path = "/desativarTipoCrime")
    public ResponseDto desativarTipoCrime(@RequestParam(required = true) int id) {
        return Tipo_CrimeService.desativarTipoCrime(id);
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Ativar tipo de crime", description = "Ativa um tipo de crime desativado a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) do tipo de crime") })
    @PutMapping(path = "/ativarTipoCrime")
    public ResponseDto ativarTipoCrime(@RequestParam(required = true) int id) {
        return Tipo_CrimeService.ativarTipoCrime(id);
    }

    @Operation(tags = {
            "Tipo de crime" }, summary = "Atualizar tipo de crime", description = "Atualiza os dados de um tipo de crime ativo a partir do ID.")
    @PutMapping(path = "/atualizarTipoCrime")
    public ResponseDto atualizarTipoCrime(@RequestBody Tipo_CrimeInputUpdateDto tipoCrime) {
        return Tipo_CrimeService.atualizarTipoCrime(tipoCrime.getNome(), tipoCrime.getLogotipo(), tipoCrime.getId());
    }
}
