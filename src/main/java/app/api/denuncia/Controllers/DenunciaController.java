package app.api.denuncia.Controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Denuncia;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Services.DenunciaService;
import app.api.denuncia.Utilities.GlobalFunctions;
import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@RestController
@RequestMapping(path = "/api/denuncia", produces = MediaType.APPLICATION_JSON_VALUE)
public class DenunciaController {

    private DenunciaService DenunciaService;
    private GlobalFunctions gf = new GlobalFunctions();

    public DenunciaController(DenunciaService denunciaService) {
        DenunciaService = denunciaService;
    }

    @Operation(tags = {
            "Denúncia" }, summary = "Adicionar denúncia", description = "Adiciona uma nova denúncia.")
    @PostMapping(path = "/adicionarDenuncia")
    public Response adicionarDenuncia(
            @RequestBody String denuncia) {
        return DenunciaService.adicionarDenuncia(denuncia);
    }

    @Hidden
    @PostMapping(path = "/sistemaPolicia")
    public Response sistemaPolicia(@RequestBody Denuncia denuncia) {
        return gf.getResponse(1, ResponseType.Sucesso, null, null);
    }

    @Operation(tags = {
            "Denúncia" }, summary = "Filtrar Denúncia", description = "Filtrar dados das denúncias.", parameters = {
                    @Parameter(name = "TipoQueixa", description = "Tipo de queixa da denúncia"),
                    @Parameter(name = "TipoCrime", description = "Tipo de crime da denúncia"),
                    @Parameter(name = "DataInicio", description = "A data de início"),
                    @Parameter(name = "DataFim", description = "A data de fim"),
                    @Parameter(name = "IdadeInicio", description = "A faixa etária"),
                    @Parameter(name = "IdadeFim", description = "A faixa etária"),
                    @Parameter(name = "Genero", description = "O genero"),
                    @Parameter(name = "Concelho", description = "O concelho") })
    @SecurityRequirement(name = "Bearer Authentication")
    @GetMapping(path = "/filtroDenuncia")
    public ResponseEntity<Response> filtroDenuncia(
            @RequestParam(required = true) Integer TipoQueixa,
            @RequestParam(required = true) Integer TipoCrime,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataInicio,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataFim,
            @RequestParam(required = false) Integer IdadeInicio,
            @RequestParam(required = false) Integer IdadeFim,
            @RequestParam(required = false) String Genero,
            @RequestParam(required = false) Integer Concelho) {
        return ResponseEntity.ok(DenunciaService.filtroDenuncia(TipoQueixa, TipoCrime, DataInicio, DataFim, IdadeInicio,
                IdadeFim, Genero, Concelho));
    }
}
