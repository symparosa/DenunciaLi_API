package app.api.denuncia.Services;

import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.ResponseModel;

public interface DenuncianteService {

    ResponseModel listar_ocorrencias();

    ResponseModel eliminarConta(int estado);

    ResponseModel recuperarConta(String email);

    ResponseModel validarSenhaAtual(String senha);

    ResponseModel get_by_username(String username);

    Boolean existsByIdAndEstado(int id, int estado);

    ResponseModel adicionar(String username, String nome);

    ResponseModel atualizar(DenuncianteModel Denunciante);

    ResponseModel alterarPassword(String username, String hash, String password, Boolean logado);
}
