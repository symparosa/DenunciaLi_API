package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.UtilizadorBackofficeModel;

public interface UtilizadorBackofficeService {

    ResponseDto adicionar_atualizar(UtilizadorBackofficeModel utilizador);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();
}
