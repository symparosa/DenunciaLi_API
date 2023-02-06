package app.api.denuncia.Services;

import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Models.ResponseModel;

public interface EntidadeService {
    
    ResponseModel adicionar_atualizar(EntidadeModel entidade);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();
}
