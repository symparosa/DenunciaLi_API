package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import io.swagger.v3.oas.annotations.Parameter;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Services.LocalizacaoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@Tag(name = "Localização")
@RestController
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/localizacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class LocalizacaoController {

    private LocalizacaoService LocalizacaoService;

    public LocalizacaoController(app.api.denuncia.Services.LocalizacaoService localizacaoService) {
        LocalizacaoService = localizacaoService;
    }

    @Operation(summary = "Get Concelhos", description = "Lista todos os concelhos de Cabo Verde.")
    @GetMapping(path = "/getConcelhos")
    public Response getConcelhos() {
        return LocalizacaoService.getConcelhos();
    }

    @Operation(summary = "Get Ilhas", description = "Lista todos as ilhas de Cabo Verde.")
    @GetMapping(path = "/getIlhas")
    public Response getIlhas() {
        return LocalizacaoService.getIlhas();
    }

    @Operation(summary = "Listar localizações", description = "Lista todas as localizações a partir de um concelho.", parameters = {
            @Parameter(name = "Concelho", description = "O identificador (ID) do concelho") })
    @GetMapping(path = "/listarLocalizacoes")
    public Response listarLocalizacoes(@RequestParam(required = true) String Concelho) {
        return LocalizacaoService.listarLocalizacoes(Concelho);
    }
}
