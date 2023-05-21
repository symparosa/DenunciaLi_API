package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.InformacaoLegalModel;

public interface InformacaoLegalService {

    Response listar();

    Response get_by_id(int id);

    Response getInfoByTipo(String tipo);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(InformacaoLegalModel informacaoLegal);
}
