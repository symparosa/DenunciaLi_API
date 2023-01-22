package app.api.denuncia.Controllers;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Services.EstatisticaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

@RestController
@RequestMapping(path = "/api/estatistica", produces = MediaType.APPLICATION_JSON_VALUE)
public class EstatisticaController {

        private EstatisticaService estatisticaService;

        public EstatisticaController(EstatisticaService estatisticaService) {
                this.estatisticaService = estatisticaService;
        }

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO
        // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano", description = "Lista os dados estatísticos por ano(s).", parameters = {
                                        @Parameter(name = "Ano", description = "O(s) ano(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno")
        public ResponseDto getEstatisticaDenunciaPorAno(@RequestParam(required = true) Collection<Integer> Ano) {
                return estatisticaService.getEstatisticaDenunciaPorAno(Ano);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Tipo de Queixa", description = "Lista os dados estatísticos por ano e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados")})
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_TipoQueixa(Ano, TipoQueixa);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Genero", description = "Lista os dados estatísticos por ano e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_Genero(@RequestParam(required = true) Integer Ano) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Genero(Ano);
        }

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR ANO E MÊS
        // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Mês", description = "Lista os dados estatísticos por ano e mês(meses).", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Mes", description = "O(s) mês(meses) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Mes")
        public ResponseDto getEstatisticaDenunciaPorAno_Mes(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Mes) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Mes(Ano, Mes);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Mês e Tipo de Crime", description = "Lista os dados estatísticos por ano, mês(meses) e tipo de crime.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Mes", description = "O(s) mês(meses) que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Mes_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Mes,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Mes_TipoCrime(Ano, Mes, TipoCrime);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR ANO E TIPO DE CRIME
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Tipo de Crime", description = "Lista os dados estatísticos por ano e tipo de crime.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_TipoCrime(Ano, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Tipo de Crime e Genero", description = "Lista os dados estatísticos por ano, tipo de crime e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_TipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_Genero(@RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_TipoCrime_Genero(Ano, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por ano, tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Ano, TipoCrime, TipoQueixa);
        }

        // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Faixa Etária", description = "Lista os dados estatísticos por ano e faixa etária.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria")
        public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(@RequestParam(required = true) Integer Ano) {
                return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria(Ano);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Faixa Etária e Genero", description = "Lista os dados estatísticos por ano, faixa etária e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(@RequestParam(required = true) Integer Ano) {
                return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Ano);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Faixa Etária e Tipo de Crime", description = "Lista os dados estatísticos por ano, faixa etária e tipo de crime.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Ano, TipoCrime);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR ANO E ILHA
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Ilha", description = "Lista os dados estatísticos por ano e ilha.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A(s) ilha(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Ilha) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha(Ano, Ilha);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Ilha e Genero", description = "Lista os dados estatísticos por ano, ilha e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A(s) ilha(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha_Genero(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Ilha) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha_Genero(Ano, Ilha);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Ilha e Tipo de Queixa", description = "Lista os dados estatísticos por ano, ilha e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A(s) ilha(s) que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Ano, Ilha, TipoQueixa);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Ilha e Tipo de Crime", description = "Lista os dados estatísticos por ano, ilha e tipo de crime.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A(s) ilha(s) que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Ano, Ilha, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Ilha, Tipo de Crime e Genero", description = "Lista os dados estatísticos por ano, ilha, tipo de crime e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Ano, Ilha, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Ilha, Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por ano, ilha, tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Ano, Ilha, TipoCrime,
                                TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR ANO E CONCELHO
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano e Concelho", description = "Lista os dados estatísticos por ano e concelho.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O(s) Concelho(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Concelho) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho(Ano, Concelho);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Concelho e Genero", description = "Lista os dados estatísticos por ano, concelho e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O(s) Concelho(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho_Genero(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Concelho) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho_Genero(Ano, Concelho);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Concelho e Tipo de Queixa", description = "Lista os dados estatísticos por ano, concelho e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O(s) Concelho(s) que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Ano, Concelho, TipoQueixa);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Concelho e Tipo de Crime", description = "Lista os dados estatísticos por ano, concelho e tipo de crime.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O(s) Concelho(s) que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Collection<Integer> Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Ano, Concelho, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Concelho, Tipo de Crime e Genero", description = "Lista os dados estatísticos por ano, concelho, tipo de crime e genero.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Ano, Concelho,
                                TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ano" }, summary = "Get Estatística Denúncia Por Ano, Concelho, Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por ano, concelho, tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                                        @Parameter(name = "Concelho", description = "O Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(
                        @RequestParam(required = true) Integer Ano,
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Ano, Concelho,
                                TipoCrime, TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR ILHA
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha", description = "Lista os dados estatísticos por ilha(s).", parameters = {
                                        @Parameter(name = "Ilha", description = "A(s) ilha(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha")
        public ResponseDto getEstatisticaDenunciaPorIlha(@RequestParam(required = true) Collection<Integer> Ilha) {
                return estatisticaService.getEstatisticaDenunciaPorIlha(Ilha);
        }

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha e Genero", description = "Lista os dados estatísticos por ilha e genero.", parameters = {
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha_Genero")
        public ResponseDto getEstatisticaDenunciaPorIlha_Genero(@RequestParam(required = true) Integer Ilha) {
                return estatisticaService.getEstatisticaDenunciaPorIlha_Genero(Ilha);
        }

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha e Tipo de Crime", description = "Lista os dados estatísticos por ilha e tipo de crime", parameters = {
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime(
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorIlha_TipoCrime(Ilha, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha e Tipo de Queixa", description = "Lista os dados estatísticos por ilha e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorIlha_TipoQueixa(
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorIlha_TipoQueixa(Ilha, TipoQueixa);
        }

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha, Tipo de Crime e Genero", description = "Lista os dados estatísticos por ilha, tipo de crime e genero.", parameters = {
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha_TipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_Genero(
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Ilha, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Ilha" }, summary = "Get Estatística Denúncia Por Ilha, Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por ilha, tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "Ilha", description = "A ilha que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(
                        @RequestParam(required = true) Integer Ilha,
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Ilha, TipoCrime,
                                TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR CONCELHO
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho", description = "Lista os dados estatísticos por concelho(s).", parameters = {
                                        @Parameter(name = "Concelho", description = "A(s) Concelho(s) que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho")
        public ResponseDto getEstatisticaDenunciaPorConcelho(
                        @RequestParam(required = true) Collection<Integer> Concelho) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho(Concelho);
        }

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho e Genero", description = "Lista os dados estatísticos por concelho e genero.", parameters = {
                                        @Parameter(name = "Concelho", description = "A Concelho que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho_Genero")
        public ResponseDto getEstatisticaDenunciaPorConcelho_Genero(@RequestParam(required = true) Integer Concelho) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho_Genero(Concelho);
        }

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho e Tipo de Crime", description = "Lista os dados estatísticos por concelho e tipo de crime", parameters = {
                                        @Parameter(name = "Concelho", description = "A Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime(
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho_TipoCrime(Concelho, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho e Tipo de Queixa", description = "Lista os dados estatísticos por concelho e tipo de queixa.", parameters = {
                                        @Parameter(name = "Concelho", description = "A Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorConcelho_TipoQueixa(
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho_TipoQueixa(Concelho, TipoQueixa);
        }

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho, Tipo de Crime e Genero", description = "Lista os dados estatísticos por concelho, tipo de crime e genero.", parameters = {
                                        @Parameter(name = "Concelho", description = "A Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho_TipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Concelho, TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Concelho" }, summary = "Get Estatística Denúncia Por Concelho, Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por concelho, tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "Concelho", description = "A Concelho que se quer obter os dados"),
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(
                        @RequestParam(required = true) Integer Concelho,
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Concelho, TipoCrime,
                                TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR GENERO
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Genero" }, summary = "Get Estatística Denúncia Por Genero", description = "Lista os dados estatísticos por genero.")
        @GetMapping(path = "/getEstatisticaDenunciaPorGenero")
        public ResponseDto getEstatisticaDenunciaPorGenero() {
                return estatisticaService.getEstatisticaDenunciaPorGenero();
        }

        // -------------------------------------------------------------------------
        // ESTATÍSTICA POR TIPO DE CRIME
        // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Tipo De Crime" }, summary = "Get Estatística Denúncia Por Tipo de Crime", description = "Lista os dados estatísticos por tipo de crime.", parameters = {
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorTipoCrime")
        public ResponseDto getEstatisticaDenunciaPorTipoCrime(
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorTipoCrime(TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Tipo De Crime" }, summary = "Get Estatística Denúncia Por Tipo de Crime e Genero", description = "Lista os dados estatísticos por tipo de crime e genero.", parameters = {
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorTipoCrime_Genero")
        public ResponseDto getEstatisticaDenunciaPorTipoCrime_Genero(
                        @RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorTipoCrime_Genero(TipoCrime);
        }

        @Operation(tags = {
                        "Estatística Por Tipo De Crime" }, summary = "Get Estatística Denúncia Por Tipo de Crime e Tipo de Queixa", description = "Lista os dados estatísticos por tipo de crime e tipo de queixa.", parameters = {
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados"),
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorTipoCrime_TipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorTipoCrime_TipoQueixa(
                        @RequestParam(required = true) Collection<Integer> TipoCrime,
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorTipoCrime_TipoQueixa(TipoCrime, TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR TIPO DE QUEIXA
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Tipo De Queixa" }, summary = "Get Estatística Denúncia Por Tipo de Queixa", description = "Lista os dados estatísticos por tipo de queixa.", parameters = {
                                        @Parameter(name = "TipoQueixa", description = "O(s) tipo(s) de queixa que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorTipoQueixa")
        public ResponseDto getEstatisticaDenunciaPorTipoQueixa(
                        @RequestParam(required = true) Collection<Integer> TipoQueixa) {
                return estatisticaService.getEstatisticaDenunciaPorTipoQueixa(TipoQueixa);
        }

        // // -------------------------------------------------------------------------
        // // ESTATÍSTICA POR FAIXA ETÁRIA
        // // -------------------------------------------------------------------------

        @Operation(tags = {
                        "Estatística Por Faixa Etária" }, summary = "Get Estatística Denúncia Por Faixa Etária", description = "Lista os dados estatísticos por faixa etária.")
        @GetMapping(path = "/getEstatisticaDenunciaPorFaixaEtaria")
        public ResponseDto getEstatisticaDenunciaPorFaixaEtaria() {
                return estatisticaService.getEstatisticaDenunciaPorFaixaEtaria();
        }

        @Operation(tags = {
                        "Estatística Por Faixa Etária" }, summary = "Get Estatística Denúncia Por Faixa Etária e Genero", description = "Lista os dados estatísticos por faixa etária e genero.")
        @GetMapping(path = "/getEstatisticaDenunciaPorFaixaEtaria_Genero")
        public ResponseDto getEstatisticaDenunciaPorFaixaEtaria_Genero() {
                return estatisticaService.getEstatisticaDenunciaPorFaixaEtaria_Genero();
        }

        @Operation(tags = {
                        "Estatística Por Faixa Etária" }, summary = "Get Estatística Denúncia Por Faixa Etária e Tipo de Crime", description = "Lista os dados estatísticos por faixa etária e tipo de crime.", parameters = {
                                        @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
        @GetMapping(path = "/getEstatisticaDenunciaPorFaixaEtaria_TipoCrime")
        public ResponseDto getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(@RequestParam(required = true) Collection<Integer> TipoCrime) {
                return estatisticaService.getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(TipoCrime);
        }
}
