package app.api.denuncia.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.InstituicaoInputInsertDto;
import app.api.denuncia.Dto.InstituicaoInputUpdateDto;
import app.api.denuncia.Dto.ResponseDto;
import app.api.denuncia.Services.Instituicao_ApoioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/InstituicaoApoio", produces = MediaType.APPLICATION_JSON_VALUE)
public class Instituicao_ApoioController {

        private Instituicao_ApoioService Instituicao_ApoioService;

        public Instituicao_ApoioController(Instituicao_ApoioService instituicao_ApoioService) {
                Instituicao_ApoioService = instituicao_ApoioService;
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Adicionar instituição de apoio", description = "Adiciona instituição de apoio ativo no banco de dados.")
        @PostMapping(path = "/adicionarInstituicaoApoio")
        public ResponseDto adicionarInstituicaoApoio(@RequestBody InstituicaoInputInsertDto inst) {
                return Instituicao_ApoioService.adicionarInstituicaoApoio(inst.getEmail(), inst.getEndereco(), inst.getLogotipo(), inst.getNome(), inst.getTelefone(),
                inst.getTipoCrime(), inst.getPorta(), inst.getRua());
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Listar intituições de apoio ativos", description = "Lista todos as intituições de apoio que estão ativos.")
        @GetMapping(path = "/listarInstituicaoDeApoioAtivos")
        public ResponseDto listarInstituicaoDeApoioAtivos() {
                return Instituicao_ApoioService.listarInstituicaoDeApoioAtivos();
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Listar intituições de apoio inativos", description = "Lista todos as intituições de apoio que estão inativos.")
        @GetMapping(path = "/listarInstituicaoDeApoioInativos")
        public ResponseDto listarInstituicaoDeApoioInativos() {
                return Instituicao_ApoioService.listarInstituicaoDeApoioInativos();
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Get intituição de apoio by crime", description = "Lista intituições de apoio ativos a partir do tipo de crime que as vitimas sofreram.", parameters = {
                                        @Parameter(name = "id", description = "O identificador (ID) do tipo de crime") })
        @GetMapping(path = "/getInstituicaoApoioByCrime")
        public ResponseDto getInstituicaoApoioByCrime(@RequestParam(required = true) int id) {
                return Instituicao_ApoioService.getInstituicaoApoioByCrime(id);
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Get intituição de apoio by id", description = "Lista uma intituição de apoio ativo a partir do ID.", parameters = {
                                        @Parameter(name = "id", description = "O identificador (ID) da intituição de apoio") })
        @GetMapping(path = "/getInstituicaoApoioById")
        public ResponseDto getInstituicaoApoioById(@RequestParam(required = true) int id) {
                return Instituicao_ApoioService.getInstituicaoApoioById(id);
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Desativar intituição de apoio", description = "Desativa uma intituição de apoio ativo a partir do ID.", parameters = {
                                        @Parameter(name = "id", description = "O identificador (ID) da intituição de apoio") })
        @PutMapping(path = "/desativarInstituicaoApoio")
        public ResponseDto desativarInstituicaoApoio(@RequestParam(required = true) int id) {
                return Instituicao_ApoioService.desativarInstituicaoApoio(id);
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Ativar intituição de apoio", description = "Ativa uma intituição de apoio desativado a partir do ID.", parameters = {
                                        @Parameter(name = "id", description = "O identificador (ID) da intituição de apoio") })
        @PutMapping(path = "/ativarInstituicaoApoio")
        public ResponseDto ativarInstituicaoApoio(@RequestParam(required = true) int id) {
                return Instituicao_ApoioService.ativarInstituicaoApoio(id);
        }

        @Operation(tags = {
                        "Instituição de apoio" }, summary = "Atualizar intituição de apoio", description = "Atualiza os dados de um intituição de apoio a partir do ID.")
        @PutMapping(path = "/atualizarInstituicaoApoio")
        public ResponseDto atualizarInstituicaoApoio(@RequestBody InstituicaoInputUpdateDto inst) {
                return Instituicao_ApoioService.atualizarInstituicaoApoio(inst.getEmail(), inst.getEndereco(), inst.getLogotipo(), inst.getNome(), inst.getTelefone(),
                inst.getPorta(), inst.getRua(),inst.getId());
        }
}
