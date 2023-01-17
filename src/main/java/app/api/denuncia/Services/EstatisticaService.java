package app.api.denuncia.Services;

import java.util.Collection;

import app.api.denuncia.Dto.ResponseDto;

public interface EstatisticaService {
    
    ResponseDto getEstatisticaDenunciaPorAno(Collection<Integer> ano);

    ResponseDto getEstatisticaDenunciaPorAno_Mes(Integer ano, Collection<Integer> mes);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(Integer ano, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(Integer ano, Collection<Integer> mes, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(Integer ano,Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_Genero(Integer ano,Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_TipoQueixa(Integer ano,Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime_TipoQueixa(Integer ano, Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorAno_Genero(Integer ano);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha(Integer ano, Collection<Integer> Ilhas);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_Genero(Integer ano, Collection<Integer> Ilhas);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime(Integer ano, Collection<Integer> Ilhas, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoQueixa(Integer ano, Collection<Integer> Ilhas, Collection<Integer> tipoQueixa);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_Genero(Integer ano, Integer Ilha, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixa(Integer ano, Integer Ilha, Collection<Integer> tipoCrime, Collection<Integer> tipoQueixa);
}
