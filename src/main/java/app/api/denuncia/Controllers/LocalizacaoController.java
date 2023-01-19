package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Services.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/localizacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalizacaoController {

    private LocalizacaoService LocalizacaoService;

    public LocalizacaoController(app.api.denuncia.Services.LocalizacaoService localizacaoService) {
        LocalizacaoService = localizacaoService;
    }

    @Operation(tags = {
            "Localização" }, summary = "Get localização by nome", description = "Lista todas as localizações ativas a partir do nome.", parameters = {
                    @Parameter(name = "nome", description = "O nome da localização") })
    @GetMapping(path = "/listarLocalizacoesByNome")
    public ResponseDto listarLocalizacoes(@RequestParam(required = true) String nome) {
        return LocalizacaoService.listarLocalizacoes(nome);
    }

    @Operation(tags = {
            "Localização" }, summary = "Get localização by id", description = "Lista uma localização ativa a partir do ID.", parameters = {
                    @Parameter(name = "id", description = "O identificador (ID) da localização") })
    @GetMapping(path = "/getLocalizacaoById")
    public ResponseDto getLocalizacaoById(@RequestParam(required = true) int id) {
        return LocalizacaoService.getLocalizacaoById(id);
    }

    @Operation(tags = {
            "Localização" }, summary = "Get Ilhas", description = "Lista todas as ilhas de Cabo Verde.")
    @GetMapping(path = "/getIlhas")
    public ResponseDto getIlhas() {
        return LocalizacaoService.getIlhas();
    }

    @Operation(tags = {
            "Localização" }, summary = "Get Concelhos", description = "Lista todos os concelhos de Cabo Verde.")
    @GetMapping(path = "/getConcelhos")
    public ResponseDto getConcelhos() {
        return LocalizacaoService.getConcelhos();
    }
}
