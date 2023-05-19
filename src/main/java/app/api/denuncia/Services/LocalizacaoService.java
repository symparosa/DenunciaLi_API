package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.LocalizacaoModel;

public interface LocalizacaoService {

    Response listarLocalizacoes(String concelho);

    Response getIlhas();

    Response getConcelhos();

    Boolean existsLocalizacao(LocalizacaoModel local);
}
