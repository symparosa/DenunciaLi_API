package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.ContatoModel;

public interface ContatoService {

    ResponseDto adicionar_atualizar(List<ContatoModel> contatoModels);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto getInfoByIdObjeto(int id);
}
