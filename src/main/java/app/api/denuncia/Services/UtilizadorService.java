package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.UtilizadorModel;

public interface UtilizadorService {

    Response listar();

    Response get_by_email(String email);

    Response recuperarConta(String email);

    Response validarSenhaAtual(String senha);

    Response alterarEstado(int id, int estado);

    List<Integer> getIdUserContaNaoConfirmada();

    Boolean existsByIdAndEstado(int id, int estado);

    Integer eliminarUser(int estado, int idLast, int id);

    Response adicionar_atualizar(UtilizadorModel utilizador);

    Response alterarPassword(String username, String hash, String password, Boolean logado);
}
