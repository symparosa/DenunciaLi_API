package app.api.denuncia.Services;

import app.api.denuncia.Dto.ResponseDto;

public interface LocalizacaoService {

    ResponseDto listarLocalizacoes(String localizacao);

    ResponseDto getLocalizacaoById(int id);
}
