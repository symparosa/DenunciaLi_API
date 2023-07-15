package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.NoticiaModel;
import app.api.denuncia.Services.NoticiaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@Tag(name = "Notícia")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/noticia", produces = MediaType.APPLICATION_JSON_VALUE)
public class NoticiaController {

    private NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @Operation(summary = "Adicionar / Atualizar Notícia", description = "Adiciona / Atualiza notícia no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<Response> adicionar_atualizar(@RequestBody NoticiaModel noticia) {
        return ResponseEntity.ok(noticiaService.adicionar_atualizar(noticia));
    }

    @Operation(summary = "Alterar Estado Notícia", description = "Altera o estado da notícia no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da notícia"),
            @Parameter(name = "Estado", description = "O estado da notícia") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<Response> alterarEstado(
            @RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(noticiaService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Notícias", description = "Lista todos as notícias que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseEntity<Response> listar() {
        return ResponseEntity.ok(noticiaService.listar());
    }

    @Operation(summary = "Get Detalhes Notícia", description = "Lista todos os detalhes da notícia.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) da notícia") })
    @GetMapping(path = "/get_detalhes_by_id")
    public ResponseEntity<Response> get_detalhes_by_id(@RequestParam(required = true) int Id) {
        return ResponseEntity.ok(noticiaService.get_by_id(Id));
    }
}
