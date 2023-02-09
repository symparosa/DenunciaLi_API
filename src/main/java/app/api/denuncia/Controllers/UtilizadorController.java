package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Services.UtilizadorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/utilizadorBackoffice", produces = MediaType.APPLICATION_JSON_VALUE)
public class UtilizadorController {

    private UtilizadorService utilizadorBackofficeService;

    public UtilizadorController(UtilizadorService utilizadorBackofficeService) {
        this.utilizadorBackofficeService = utilizadorBackofficeService;
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Adicionar / Atualizar Utilizador", description = "Adiciona / Atualiza utilizador no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseModel adicionar_atualizar(@RequestBody UtilizadorModel utilizador) {
        return utilizadorBackofficeService.adicionar_atualizar(utilizador);
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Alterar Estado Utilizador", description = "Altera o estado do utilizador no banco de dados.", parameters = {
                    @Parameter(name = "Id", description = "O identificador (ID) do utilizador"),
                    @Parameter(name = "Estado", description = "O estado do utilizador") })
    @PutMapping(path = "/alterarEstado")
    public ResponseModel alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return utilizadorBackofficeService.alterarEstado(Id, Estado);
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Listar Utilizadores", description = "Lista todos os utilizadores que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseModel listar() {
        return utilizadorBackofficeService.listar();
    }

    @Operation(tags = {
            "Utilizador Back-Office" }, summary = "Alterar Password", description = "Altera o password do utilizador no banco de dados.", parameters = {
                    @Parameter(name = "Hash", description = "O hash"),
                    @Parameter(name = "Password1", description = "O novo password"),
                    @Parameter(name = "Password2", description = "O novo password novamente") })
    @PutMapping(path = "/alterarPassword")
    public ResponseModel alterarPassword(
        @RequestParam(required = true) String Hash,
        @RequestParam(required = true) String Password1,
        @RequestParam(required = true) String Password2) {
        return utilizadorBackofficeService.alterarPassword(Hash, Password1, Password2);
    }
}
