package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Services.DenunciaService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@RequestMapping(path = "/api/denuncia", produces = MediaType.APPLICATION_JSON_VALUE)
public class DenunciaController {

    private DenunciaService DenunciaService;

    public DenunciaController(DenunciaService denunciaService) {
        DenunciaService = denunciaService;
    }

    @Operation(tags = {
            "Denúncia" }, summary = "Adicionar denúncia", description = "Adiciona denúncia no banco de dados.")
    @PostMapping(path = "/adicionarDenuncia")
    public Response adicionarDenuncia(
            @RequestBody String denuncia) {
        return DenunciaService.adicionarDenuncia(denuncia);
    }
}
