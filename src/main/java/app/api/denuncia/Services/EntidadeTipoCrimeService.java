package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;

public interface EntidadeTipoCrimeService {
    
    Response adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime);

    Response alterarEstado(int id, int estado);

    Response getInfoByEntidade(int id);
}
