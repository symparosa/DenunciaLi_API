package app.api.denuncia.Controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Services.AuditoriaService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Tag(name = "Auditoria")
@ApiResponse(responseCode = "200", description = "Success response.")
// @SecurityRequirement(name = "Bearer Authentication")
// @PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping(path = "/api/auditoria", produces = MediaType.APPLICATION_JSON_VALUE)
public class AuditoriaController {

    private AuditoriaService auditoriaService;

    public AuditoriaController(AuditoriaService auditoriaService) {
        this.auditoriaService = auditoriaService;
    }

    @Operation(summary = "Filtro Auditoria", description = "Filtrar dados em auditoria.", parameters = {
            @Parameter(name = "Accao", description = "A acção efetuada"),
            @Parameter(name = "TipoObjeto", description = "O tipo de objeto afetado"),
            @Parameter(name = "DataInicio", description = "A data de início"),
            @Parameter(name = "DataFim", description = "A data de fim") })
    @GetMapping(path = "/filtroAuditoria")
    public ResponseEntity<Response> filtroAuditoria(
            @RequestParam(required = true) String Accao,
            @RequestParam(required = true) String TipoObjeto,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataInicio,
            @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataFim) {
        return ResponseEntity.ok(auditoriaService.filtroAuditoria(Accao, TipoObjeto, DataInicio, DataFim));
    }
}
