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

import app.api.denuncia.Models.BannerModel;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Services.BannerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Banner")
@ApiResponse(responseCode = "200", description = "Success response.")
@SecurityRequirement(name = "Bearer Authentication")
@RequestMapping(path = "/api/banner", produces = MediaType.APPLICATION_JSON_VALUE)
public class BannerController {

    private BannerService bannerService;

    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @Operation(summary = "Adicionar / Atualizar Banner", description = "Adiciona / Atualiza banner no banco de dados.")
    @PostMapping(path = "/adicionar_atualizar")
    public ResponseEntity<ResponseModel> adicionar_atualizar(@RequestBody BannerModel banner) {
        return ResponseEntity.ok(bannerService.adicionar_atualizar(banner));
    }

    @Operation(summary = "Alterar Estado Banner", description = "Altera o estado do banner no banco de dados.", parameters = {
            @Parameter(name = "Id", description = "O identificador (ID) do banner"),
            @Parameter(name = "Estado", description = "O estado do banner") })
    @PutMapping(path = "/alterarEstado")
    public ResponseEntity<ResponseModel> alterarEstado(@RequestParam(required = true) int Id,
            @RequestParam(required = true) int Estado) {
        return ResponseEntity.ok(bannerService.alterarEstado(Id, Estado));
    }

    @Operation(summary = "Listar Banners", description = "Lista todos os banners que estão no banco de dados.")
    @GetMapping(path = "/listar")
    public ResponseEntity<ResponseModel> listar() {
        return ResponseEntity.ok(bannerService.listar());
    }
}
