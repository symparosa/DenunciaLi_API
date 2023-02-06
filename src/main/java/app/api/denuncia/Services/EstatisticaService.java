package app.api.denuncia.Services;

import java.util.Collection;

import app.api.denuncia.Models.ResponseModel;

public interface EstatisticaService {

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorAno(Collection<Integer> ano);

    ResponseModel getEstatisticaDenunciaPorAno_Genero(Integer ano);

    ResponseModel getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E MÊS
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes);

    ResponseModel getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes,
            Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E TIPO DE CRIME
    // -------------------------------------------------------------------------
    ResponseModel getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano);

    ResponseModel getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano);

    ResponseModel getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E ILHA
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas);

    ResponseModel getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas);

    ResponseModel getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoQueixa);

    ResponseModel getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E CONCELHO
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorAno_Concelho(Integer ano, Collection<Integer> Concelhos);

    ResponseModel getEstatisticaDenunciaPorAno_Concelho_Genero(Integer ano, Collection<Integer> Concelhos);

    ResponseModel getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoQueixa);

    ResponseModel getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ILHA
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorIlha(Collection<Integer> Ilha);

    ResponseModel getEstatisticaDenunciaPorIlha_Genero(Integer Ilha);

    ResponseModel getEstatisticaDenunciaPorIlha_TipoCrime(Integer Ilha, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorIlha_TipoQueixa(Integer Ilha, Collection<Integer> tipoQueixa);

    ResponseModel getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Integer Ilha, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Integer Ilha, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR CONCELHO
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorConcelho(Collection<Integer> Concelho);

    ResponseModel getEstatisticaDenunciaPorConcelho_Genero(Integer Concelho);

    ResponseModel getEstatisticaDenunciaPorConcelho_TipoCrime(Integer Concelho, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorConcelho_TipoQueixa(Integer Concelho, Collection<Integer> tipoQueixa);

    ResponseModel getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Integer Concelho, Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Integer Concelho, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR GENERO
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorGenero();

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE CRIME
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorTipoCrime(Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorTipoCrime_Genero(Collection<Integer> tipoCrime);

    ResponseModel getEstatisticaDenunciaPorTipoCrime_TipoQueixa(Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE QUEIXA
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorTipoQueixa(Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    ResponseModel getEstatisticaDenunciaPorFaixaEtaria();

    ResponseModel getEstatisticaDenunciaPorFaixaEtaria_Genero();

    ResponseModel getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(Collection<Integer> tipoCrime);
}
