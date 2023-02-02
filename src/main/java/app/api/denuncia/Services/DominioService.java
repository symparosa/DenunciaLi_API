package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.DominioModel;

public interface DominioService {

    ResponseDto adicionar_atualizar(List<DominioModel> dominio);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();

    ResponseDto getDominio(String dominio);
}
