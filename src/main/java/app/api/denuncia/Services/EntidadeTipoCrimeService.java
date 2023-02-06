package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Models.EntidadeTipoCrimeModel;
import app.api.denuncia.Models.ResponseModel;

public interface EntidadeTipoCrimeService {
    
    ResponseModel adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel getInfoByEntidade(int id);
}
