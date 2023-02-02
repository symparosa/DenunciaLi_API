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
import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Services.EntidadeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/entidade", produces = MediaType.APPLICATION_JSON_VALUE)
public class EntidadeController {

    private EntidadeService entidadeService;

    public EntidadeController(EntidadeService entidadeService) {
        this.entidadeService = entidadeService;
    }

    @Operation(tags = {
            "Entidade" }, summary = "Adicionar / Atualizar Entidade", description = "Adiciona / Atualiza entidade no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseDto adicionar_atualizar(@RequestBody EntidadeModel entidade) {
        return entidadeService.adicionar_atualizar(entidade);
    }

    @Operation(tags = {
            "Entidade" }, summary = "Alterar Estado Entidade", description = "Altera o estado da entidade no banco de dados.", parameters = {
                    @Parameter(name = "Id", description = "O identificador (ID) da entidade"),
                    @Parameter(name = "Estado", description = "O estado da entidade") })
    @PutMapping(path = "/alterarEstado")
    public ResponseDto alterarEstado(@RequestParam(required = true) int Id, @RequestParam(required = true) int Estado) {
        return entidadeService.alterarEstado(Id, Estado);
    }

    @Operation(tags = {
            "Entidade" }, summary = "Listar Entidades", description = "Lista todas as entidades que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseDto listar() {
        return entidadeService.listar();
    }
}
