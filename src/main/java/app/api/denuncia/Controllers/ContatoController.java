package app.api.denuncia.Controllers;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.ContatoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/contato", produces = MediaType.APPLICATION_JSON_VALUE)
public class ContatoController {

    private ContatoService ContatoService;

    public ContatoController(ContatoService contatoService) {
        ContatoService = contatoService;
    }

    @Operation(tags = {
            "Contato" }, summary = "Adicionar / Atualizar Contato", description = "Adiciona / Atualiza contato no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseModel adicionar_atualizar(@RequestBody List<ContatoModel> contato) {
        return ContatoService.adicionar_atualizar(contato);
    }

    @Operation(tags = {
            "Contato" }, summary = "Alterar Estado Contato", description = "Altera o estado do contato no banco de dados.", parameters = {
                    @Parameter(name = "Id", description = "O identificador (ID) do contato"),
                    @Parameter(name = "Estado", description = "O estado do contato") })
    @PutMapping(path = "/alterarEstado")
    public ResponseModel alterarEstado(@RequestParam(required = true) int Id, @RequestParam(required = true) int Estado) {
        return ContatoService.alterarEstado(Id, Estado);
    }

    @Operation(tags = {
            "Contato" }, summary = "Get Info By IdObjeto e TipoObjeto", description = "Lista todos os dados a partir do id objeto e do tipo de objeto.", parameters = {
                    @Parameter(name = "IdObjeto", description = "O id objeto que se quer obter dados"),
                    @Parameter(name = "TipoObjeto", description = "O tipo objeto que se quer obter dados") })
    @GetMapping(path = "/getInfoByIdObjeto")
    public ResponseModel getInfoByIdObjeto(
        @RequestParam(required = true) int IdObjeto,
        @RequestParam(required = true) String TipoObjeto) {
        return ContatoService.getInfoByIdObjeto(IdObjeto, TipoObjeto);
    }
}
