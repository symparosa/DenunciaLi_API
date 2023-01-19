package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface LocalizacaoService {

    ResponseDto listarLocalizacoes(String localizacao);

    ResponseDto getLocalizacaoById(int id);

    ResponseDto getIlhas();

    ResponseDto getConcelhos();
}
