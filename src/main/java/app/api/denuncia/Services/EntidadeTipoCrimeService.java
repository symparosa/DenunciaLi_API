package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;

public interface EntidadeTipoCrimeService {

    Response getInfoByEntidade(int id);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime);
}
