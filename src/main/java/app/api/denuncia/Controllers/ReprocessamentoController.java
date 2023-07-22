package app.api.denuncia.Controllers;

import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Integration.Integrate.IntegrateService;
import app.api.denuncia.Services.ReprocessamentoService;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Tag(name = "Reprocessamento")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/reprocessamento", produces = MediaType.APPLICATION_JSON_VALUE)
public class ReprocessamentoController {

        private IntegrateService integrateService;
        private ReprocessamentoService reprocessamentoService;

        public ReprocessamentoController(IntegrateService integrateService,
                        ReprocessamentoService reprocessamentoService) {
                this.integrateService = integrateService;
                this.reprocessamentoService = reprocessamentoService;
        }

        @Operation(summary = "Filtro Reprocessamento Email", description = "Filtrar dados de email em reprocessamento.", parameters = {
                        @Parameter(name = "DataInicio", description = "A data de início"),
                        @Parameter(name = "DataFim", description = "A data de fim"),
                        @Parameter(name = "Estado", description = "O estado do reprocessamento") })
        @GetMapping(path = "/filtroReprocessamentoEmail")
        public ResponseEntity<Response> filtroReprocessamentoEmail(
                        @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataInicio,
                        @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataFim,
                        @RequestParam(required = true) Integer Estado) {
                return ResponseEntity
                                .ok(reprocessamentoService.filtroReprocessamentoEmail(DataInicio, DataFim, Estado));
        }

        @Operation(summary = "Alterar Estado Reprocessamento", description = "Altera o estado de um reprocessamento.", parameters = {
                        @Parameter(name = "Id", description = "O identificador (ID) do reprocessamento"),
                        @Parameter(name = "Estado", description = "O estado do reprocessamento") })
        @PutMapping(path = "/alterarEstado")
        public ResponseEntity<Response> alterarEstado(@RequestParam(required = true) int Id,
                        @RequestParam(required = true) int Estado) {
                return ResponseEntity.ok(reprocessamentoService.alterarEstado(Id, Estado));
        }

        @Operation(summary = "Reprocessamento Manual Email", description = "Reprocessar email manualmente.", parameters = {
                        @Parameter(name = "Id", description = "O identificador (ID) do reprocessamento") })
        @PostMapping(path = "/reprocessamentoManualEmail")
        public ResponseEntity<Response> reprocessamentoManualEmail(
                        @RequestParam(required = true) Integer Id) {
                return ResponseEntity.ok(reprocessamentoService.reprocessamentoManualEmail(Id));
        }

        @Operation(summary = "Filtro Reprocessamento Denúncia", description = "Filtrar dados de denúncia em reprocessamento.", parameters = {
                        @Parameter(name = "DataInicio", description = "A data de início"),
                        @Parameter(name = "DataFim", description = "A data de fim"),
                        @Parameter(name = "Estado", description = "O estado do reprocessamento") })
        @GetMapping(path = "/filtroReprocessamentoDenuncia")
        public ResponseEntity<Response> filtroReprocessamentoDenuncia(
                        @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataInicio,
                        @RequestParam(required = true) @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate DataFim,
                        @RequestParam(required = true) Integer Estado) {
                return ResponseEntity
                                .ok(reprocessamentoService.filtroReprocessamentoDenuncia(DataInicio, DataFim, Estado));
        }

        @Operation(summary = "Reprocessamento Manual Denúncia", description = "Reprocessar denúncia manualmente.", parameters = {
                        @Parameter(name = "Id", description = "O identificador (ID) do reprocessamento") })
        @PostMapping(path = "/reprocessamentoManualDenuncia")
        public ResponseEntity<Response> reprocessamentoManualDenuncia(
                        @RequestParam(required = true) Integer Id) {
                return ResponseEntity.ok(integrateService.reprocessamentoManualDenuncia(Id));
        }
}
