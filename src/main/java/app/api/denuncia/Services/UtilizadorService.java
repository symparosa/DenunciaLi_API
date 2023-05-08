package app.api.denuncia.Services;

import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;

public interface UtilizadorService {

    ResponseModel listar();

    ResponseModel get_by_id(int id);

    ResponseModel recuperarConta(String email);

    ResponseModel alterarEstado(int id, int estado);

    Boolean existsByIdAndEstado(int id, int estado);

    ResponseModel adicionar_atualizar(UtilizadorModel utilizador);

    ResponseModel alterarPassword(String username, String hash, String password);
}
