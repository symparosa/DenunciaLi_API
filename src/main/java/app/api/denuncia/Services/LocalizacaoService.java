package app.api.denuncia.Services;

import app.api.denuncia.Models.LocalizacaoModel;
import app.api.denuncia.Models.ResponseModel;

public interface LocalizacaoService {

    ResponseModel listarLocalizacoes();

    ResponseModel getIlhas();

    ResponseModel getConcelhos();

    Boolean existsLocalizacao(LocalizacaoModel local);
}
