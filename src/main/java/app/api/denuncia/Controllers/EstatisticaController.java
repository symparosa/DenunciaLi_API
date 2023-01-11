package app.api.denuncia.Controllers;

import java.util.Collection;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.ResponseDto;
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

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano", description = "Lista os dados estatísticos por ano(s).", parameters = {
                    @Parameter(name = "Ano", description = "O(s) ano(s) que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno")
    public ResponseDto getEstatisticaDenunciaPorAno(@RequestParam(required = true) Collection<String> Ano) {
        return estatisticaService.getEstatisticaDenunciaPorAno(Ano);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano e Mês", description = "Lista os dados estatísticos por ano e mês(meses).", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                    @Parameter(name = "Mes", description = "O(s) mês(meses) que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_Mes")
    public ResponseDto getEstatisticaDenunciaPorAno_Mes(
            @RequestParam(required = true) String Ano,
            @RequestParam(required = true) Collection<Integer> Mes) {
        return estatisticaService.getEstatisticaDenunciaPorAno_Mes(Ano, Mes);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano e Tipo de Crime", description = "Lista os dados estatísticos por ano e mês(meses).", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                    @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_TipoCrime")
    public ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(
            @RequestParam(required = true) String Ano,
            @RequestParam(required = true) Collection<Integer> TipoCrime) {
        return estatisticaService.getEstatisticaDenunciaPorAno_TipoCrime(Ano, TipoCrime);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano, Mês e Tipo de Crime", description = "Lista os dados estatísticos por ano e mês(meses).", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                    @Parameter(name = "Mes", description = "O(s) mês(meses) que se quer obter os dados"),
                    @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_Mes_TipoCrime")
    public ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(
            @RequestParam(required = true) String Ano,
            @RequestParam(required = true) Collection<Integer> Mes,
            @RequestParam(required = true) Collection<Integer> TipoCrime) {
        return estatisticaService.getEstatisticaDenunciaPorAno_Mes_TipoCrime(Ano, Mes, TipoCrime);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano e Faixa Etária", description = "Lista os dados estatísticos por ano e faixa etária.", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria")
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(@RequestParam(required = true) String Ano) {
        return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria(Ano);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano, Faixa Etária e Genero", description = "Lista os dados estatísticos por ano, faixa etária e genero.", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria_Genero")
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(@RequestParam(required = true) String Ano) {
        return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Ano);
    }

    @Operation(tags = {
            "Estatística" }, summary = "Get Estatística Denuncia Por Ano, Faixa Etária e Tipo de Crime", description = "Lista os dados estatísticos por ano, faixa etária e tipo de crime.", parameters = {
                    @Parameter(name = "Ano", description = "O ano que se quer obter os dados"),
                    @Parameter(name = "TipoCrime", description = "O(s) tipo(s) de crime que se quer obter os dados") })
    @GetMapping(path = "/getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime")
    public ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(@RequestParam(required = true) String Ano,@RequestParam(required = true) Collection<Integer> TipoCrime) {
        return estatisticaService.getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Ano,TipoCrime);
    }
}
