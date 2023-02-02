package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.UtilizadorBackofficeModel;
import app.api.denuncia.Services.UtilizadorBackofficeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/utilizadorBackoffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilizadorBackofficeController {

    private UtilizadorBackofficeService utilizadorBackofficeService;

    public UtilizadorBackofficeController(UtilizadorBackofficeService utilizadorBackofficeService) {
        this.utilizadorBackofficeService = utilizadorBackofficeService;
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Adicionar / Atualizar Utilizador", description = "Adiciona / Atualiza utilizador no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseDto adicionar_atualizar(@RequestBody UtilizadorBackofficeModel utilizador) {
        return utilizadorBackofficeService.adicionar_atualizar(utilizador);
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Alterar Estado Utilizador", description = "Altera o estado do utilizador no banco de dados.", parameters = {
                    @Parameter(name = "Id", description = "O identificador (ID) do utilizador"),
                    @Parameter(name = "Estado", description = "O estado do utilizador") })
    @PutMapping(path = "/alterarEstado")
    public ResponseDto alterarEstado(@RequestParam(required = true) int Id, @RequestParam(required = true) int Estado) {
        return utilizadorBackofficeService.alterarEstado(Id, Estado);
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Listar Utilizadores", description = "Lista todos os utilizadores que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseDto listar() {
        return utilizadorBackofficeService.listar();
    }
}
