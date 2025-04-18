package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.DenuncianteModel;

public interface DenuncianteService {

    Response getFaixaEtaria();

    Response eliminarConta(int estado);

    Response recuperarConta(String email);

    DenuncianteModel findbyid(Integer id);

    Response validarSenhaAtual(String senha);

    Response get_by_username(String username);

    List<Integer> getIdUserContaNaoConfirmada();

    Boolean existsByIdAndEstado(int id, int estado);

    Response adicionar(String username, String nome);

    Response atualizar(DenuncianteModel Denunciante);

    Integer alterarEstado(int estado, int idLast, int id);

    Response alterarPassword(String username, String hash, String password, Boolean logado);
}
