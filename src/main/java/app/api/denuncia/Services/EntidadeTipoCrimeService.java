package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.EntidadeTipoCrimeModel;

public interface EntidadeTipoCrimeService {
    
    ResponseDto adicionar_atualizar(List<EntidadeTipoCrimeModel> entidadeTipoCrime);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto getInfoByEntidade(int id);
}
