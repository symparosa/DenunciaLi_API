package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.LocalizacaoModel;

public interface LocalizacaoService {

    Response getIlhas();

    Response getConcelhos();

    Response listarLocalizacoes(String concelho);

    Boolean existsLocalizacao(LocalizacaoModel local);
}
