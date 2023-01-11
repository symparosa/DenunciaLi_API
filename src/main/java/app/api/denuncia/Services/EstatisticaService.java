package app.api.denuncia.Services;

import java.util.Collection;

import app.api.denuncia.Dto.ResponseDto;

public interface EstatisticaService {
    
    ResponseDto getEstatisticaDenunciaPorAno(Collection<String> ano);

    ResponseDto getEstatisticaDenunciaPorAno_Mes(String ano, Collection<Integer> mes);

    ResponseDto getEstatisticaDenunciaPorAno_TipoCrime(String ano, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_Mes_TipoCrime(String ano, Collection<Integer> mes, Collection<Integer> tipoCrime);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria(String ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_Genero(String ano);

    ResponseDto getEstatisticaDenunciaPorAno_FaixaEtaria_TipoCrime(String ano,Collection<Integer> tipoCrime);
}
