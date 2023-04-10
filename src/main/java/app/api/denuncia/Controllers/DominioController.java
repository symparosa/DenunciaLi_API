package app.api.denuncia.Controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.DominioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Domínio")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/dominio", produces = MediaType.APPLICATION_JSON_VALUE)
public class DominioController {

    private DominioService dominioservice;

    public DominioController(DominioService dominioservice) {
        this.dominioservice = dominioservice;
    }

    @Operation(summary = "Adicionar / Atualizar Domínio", description = "Adiciona / Atualiza domínios no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<ResponseModel> adicionar_atualizar(@RequestBody List<DominioModel> dominios) {
        return ResponseEntity.ok(dominioservice.adicionar_atualizar(dominios));
    }

    @Operation(summary = "Alterar Estado Domínio", description = "Altera o estado do domínio no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do domínio"),
            @Parameter(name = "Estado", description = "O estado do domínio") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<ResponseModel> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(dominioservice.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Domínios", description = "Lista todos os domínios que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseEntity<ResponseModel> listar() {
        return ResponseEntity.ok(dominioservice.listar());
    }

    @Operation(summary = "Get Domínio", description = "Lista todos os dados a partir do domínio.", parameters = {
            @Parameter(name = "Dominio", description = "O domínio que se quer obter os dados") })
    @GetMapping(path = "/getDominio")
    public ResponseEntity<ResponseModel> getDominio(@RequestParam(required = true) String Dominio) {
        return ResponseEntity.ok(dominioservice.getDominio(Dominio));
    }
}
