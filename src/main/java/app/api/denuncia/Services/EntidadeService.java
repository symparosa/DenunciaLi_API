package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.EntidadeModel;

public interface EntidadeService {
    
    ResponseDto adicionar_atualizar(EntidadeModel entidade);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();
}
