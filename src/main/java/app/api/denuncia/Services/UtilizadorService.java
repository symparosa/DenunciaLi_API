package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.UtilizadorModel;

public interface UtilizadorService {

    Response listar();

    Response get_by_id(int id);

    Response recuperarConta(String email);

    Response alterarEstado(int id, int estado);

    Boolean existsByIdAndEstado(int id, int estado);

    Response adicionar_atualizar(UtilizadorModel utilizador);

    Response alterarPassword(String username, String hash, String password);
}
