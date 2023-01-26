// package app.api.denuncia.Controllers;

// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.RequestBody;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import app.api.denuncia.Dto.Response.ResponseDto;
// import app.api.denuncia.Models.DenunciaModel;
// import app.api.denuncia.Services.DenunciaService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.Parameter;

// @RestController
// @RequestMapping(path = "/api/denuncia", produces = MediaType.APPLICATION_JSON_VALUE)
// public class DenunciaController {

//     private DenunciaService DenunciaService;

//     public DenunciaController(DenunciaService denunciaService) {
//         DenunciaService = denunciaService;
//     }

//     @Operation(tags = {
//             "Denúncia" }, summary = "Adicionar denúncia", description = "Adiciona denúncia ativo no banco de dados.")
//     @PostMapping(path = "/adicionarDenuncia")
//     public ResponseDto adicionarDenuncia(@RequestBody DenunciaModel denuncia) {
//         return DenunciaService.adicionarDenuncia(denuncia);
//     }

//     @Operation(tags = {
//             "Denúncia" }, summary = "Get denúncia by userID", description = "Lista uma denúncia ativo a partir do ID do utilzador.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) de denúncia") })
//     @GetMapping(path = "/listarDenunciasByUserId")
//     public ResponseDto listarDenunciasByUserId(@RequestParam(required = true) int id) {
//         return DenunciaService.listarDenunciasByUserId(id);
//     }

//     @Operation(tags = {
//             "Denúncia" }, summary = "Get denúncia by id", description = "Lista uma denúncia ativo a partir do ID.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) de denúncia") })
//     @GetMapping(path = "/getDenunciaById")
//     public ResponseDto getDenunciaById(@RequestParam(required = true) int id) {
//         return DenunciaService.getDenunciaById(id);
//     }
// }
