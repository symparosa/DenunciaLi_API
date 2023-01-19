package app.api.denuncia.Services;

import java.util.Collection;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface EstatisticaService {

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorAno(Collection<Integer> ano);

    ResponseDto getEstatisticaDenunciaPorAno_Genero(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E MÊS
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes);

    ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes,
            Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E TIPO DE CRIME
    // -------------------------------------------------------------------------
    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E ILHA
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E CONCELHO
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorAno_Concelho(Integer ano, Collection<Integer> Concelhos);

    ResponseDto getEstatisticaDenunciaPorAno_Concelho_Genero(Integer ano, Collection<Integer> Concelhos);

    ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ILHA
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorIlha(Collection<Integer> Ilha);

    ResponseDto getEstatisticaDenunciaPorIlha_Genero(Integer Ilha);

    ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime(Integer Ilha, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorIlha_TipoQueixa(Integer Ilha, Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Integer Ilha, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Integer Ilha, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR CONCELHO
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorConcelho(Collection<Integer> Concelho);

    ResponseDto getEstatisticaDenunciaPorConcelho_Genero(Integer Concelho);

    ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime(Integer Concelho, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorConcelho_TipoQueixa(Integer Concelho, Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Integer Concelho, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Integer Concelho, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR GENERO
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorGenero();

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE CRIME
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorTipoCrime(Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorTipoCrime_Genero(Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorTipoCrime_TipoQueixa(Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE QUEIXA
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorTipoQueixa(Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    ResponseDto getEstatisticaDenunciaPorFaixaEtaria();

    ResponseDto getEstatisticaDenunciaPorFaixaEtaria_Genero();

    ResponseDto getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(Collection<Integer> tipoCrime);
}
