// package app.api.denuncia.Controllers;

// import org.springframework.http.MediaType;
// import org.springframework.web.bind.annotation.GetMapping;
// import org.springframework.web.bind.annotation.PostMapping;
// import org.springframework.web.bind.annotation.PutMapping;
// import org.springframework.web.bind.annotation.RequestMapping;
// import org.springframework.web.bind.annotation.RequestParam;
// import org.springframework.web.bind.annotation.RestController;

// import app.api.denuncia.Dto.Response.ResponseDto;
// import app.api.denuncia.Services.Tipo_ArquivoService;
// import io.swagger.v3.oas.annotations.Operation;
// import io.swagger.v3.oas.annotations.Parameter;

// @RestController
// @RequestMapping(path = "/api/tipoArquivo", produces = MediaType.APPLICATION_JSON_VALUE)
// public class Tipo_ArquivoController {

//     private Tipo_ArquivoService Tipo_ArquivoService;

//     public Tipo_ArquivoController(Tipo_ArquivoService tipo_ArquivoService) {
//         Tipo_ArquivoService = tipo_ArquivoService;
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Adicionar tipo de arquivo", description = "Adiciona tipo de arquivo ativo no banco de dados.", parameters = {
//                     @Parameter(name = "nome", description = "O nome do tipo de arquivo") })
//     @PostMapping(path = "/adicionarTipoArquivo")
//     public ResponseDto adicionarTipoArquivo(@RequestParam(required = true) String nome) {
//         return Tipo_ArquivoService.adicionarTipoArquivo(nome);
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Listar tipo de arquivos ativos", description = "Lista todos os tipos de arquivos que estão ativos.")
//     @GetMapping(path = "/listarTipoArquivosAtivos")
//     public ResponseDto listarTipoArquivosAtivos() {
//         return Tipo_ArquivoService.listarTipoArquivosAtivos();
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Listar tipo de arquivos inativos", description = "Lista todos os tipos de arquivos que estão inativos.")
//     @GetMapping(path = "/listarTipoArquivosInativos")
//     public ResponseDto listarTipoArquivosInativos() {
//         return Tipo_ArquivoService.listarTipoArquivosInativos();
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Get tipo de arquivo by id", description = "Lista um tipo de arquivo ativo a partir do ID.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) do tipo de arquivo") })
//     @GetMapping(path = "/getTipoArquivoById")
//     public ResponseDto getTipoArquivoById(@RequestParam(required = true) int id) {
//         return Tipo_ArquivoService.getTipoArquivoById(id);
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Desativar tipo de arquivo", description = "Desativa um tipo de arquivo ativo a partir do ID.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) do tipo de arquivo") })
//     @PutMapping(path = "/desativarTipoArquivo")
//     public ResponseDto desativarTipoArquivo(@RequestParam(required = true) int id) {
//         return Tipo_ArquivoService.desativarTipoArquivo(id);
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Ativar tipo de arquivo", description = "Ativa um tipo de arquivo desativado a partir do ID.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) do tipo de arquivo") })
//     @PutMapping(path = "/ativarTipoArquivo")
//     public ResponseDto ativarTipoArquivo(@RequestParam(required = true) int id) {
//         return Tipo_ArquivoService.ativarTipoArquivo(id);
//     }

//     @Operation(tags = {
//             "Tipo de arquivo" }, summary = "Atualizar tipo de arquivo", description = "Atualiza os dados de um tipo de arquivo ativo a partir do ID.", parameters = {
//                     @Parameter(name = "id", description = "O identificador (ID) do tipo de arquivo"),
//                     @Parameter(name = "nome", description = "O nome do tipo de arquivo")
//             })
//     @PutMapping(path = "/atualizarTipoArquivo")
//     public ResponseDto atualizarTipoArquivo(@RequestParam(required = true) String nome,
//             @RequestParam(required = true) int id) {
//         return Tipo_ArquivoService.atualizarTipoArquivo(nome, id);
//     }
// }
