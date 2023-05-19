package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.InformacaoLegalModel;

public interface InformacaoLegalService {
    
    Response adicionar_atualizar(InformacaoLegalModel informacaoLegal);

    Response alterarEstado(int id, int estado);

    Response listar();

    Response getInfoByTipo(String tipo);

    Response get_by_id(int id);
}
