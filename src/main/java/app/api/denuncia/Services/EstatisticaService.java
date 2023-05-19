package app.api.denuncia.Services;

import java.util.Collection;

import app.api.denuncia.Dto.Response;

public interface EstatisticaService {

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorAno(Collection<Integer> ano);

    Response getEstatisticaDenunciaPorAno_Genero(Integer ano);

    Response getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E MÊS
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes);

    Response getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes,
            Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E TIPO DE CRIME
    // -------------------------------------------------------------------------
    Response getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano);

    Response getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano);

    Response getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E ILHA
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas);

    Response getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas);

    Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas,
            Collection<Integer> tipoQueixa);

    Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ANO E CONCELHO
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorAno_Concelho(Integer ano, Collection<Integer> Concelhos);

    Response getEstatisticaDenunciaPorAno_Concelho_Genero(Integer ano, Collection<Integer> Concelhos);

    Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_Concelho_TipoQueixa(Integer ano, Collection<Integer> Concelhos,
            Collection<Integer> tipoQueixa);

    Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime_Genero(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixa(Integer ano, Integer Concelho,
            Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR ILHA
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorIlha(Collection<Integer> Ilha);

    Response getEstatisticaDenunciaPorIlha_Genero(Integer Ilha);

    Response getEstatisticaDenunciaPorIlha_TipoCrime(Integer Ilha, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorIlha_TipoQueixa(Integer Ilha, Collection<Integer> tipoQueixa);

    Response getEstatisticaDenunciaPorIlha_TipoCrime_Genero(Integer Ilha, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorIlha_TipoCrime_TipoQueixa(Integer Ilha, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR CONCELHO
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorConcelho(Collection<Integer> Concelho);

    Response getEstatisticaDenunciaPorConcelho_Genero(Integer Concelho);

    Response getEstatisticaDenunciaPorConcelho_TipoCrime(Integer Concelho, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorConcelho_TipoQueixa(Integer Concelho, Collection<Integer> tipoQueixa);

    Response getEstatisticaDenunciaPorConcelho_TipoCrime_Genero(Integer Concelho, Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixa(Integer Concelho, Collection<Integer> tipoCrime,
            Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR GENERO
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorGenero();

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE CRIME
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorTipoCrime(Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorTipoCrime_Genero(Collection<Integer> tipoCrime);

    Response getEstatisticaDenunciaPorTipoCrime_TipoQueixa(Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR TIPO DE QUEIXA
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorTipoQueixa(Collection<Integer> tipoQueixa);

    // -------------------------------------------------------------------------
    // ESTATÍSTICA POR FAIXA ETÁRIA
    // -------------------------------------------------------------------------

    Response getEstatisticaDenunciaPorFaixaEtaria();

    Response getEstatisticaDenunciaPorFaixaEtaria_Genero();

    Response getEstatisticaDenunciaPorFaixaEtaria_TipoCrime(Collection<Integer> tipoCrime);
}
