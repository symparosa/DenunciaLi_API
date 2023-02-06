package app.api.denuncia.Services;

import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;

public interface UtilizadorService {

    ResponseModel adicionar_atualizar(UtilizadorModel utilizador);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();

    ResponseModel alterarPassword(String hashAtual, String passwordNovo1, String passwordNovo2);
}
