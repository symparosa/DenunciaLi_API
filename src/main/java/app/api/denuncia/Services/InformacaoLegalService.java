package app.api.denuncia.Services;

import app.api.denuncia.Models.InformacaoLegalModel;
import app.api.denuncia.Models.ResponseModel;

public interface InformacaoLegalService {
    
    ResponseModel adicionar_atualizar(InformacaoLegalModel informacaoLegal);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();
}
