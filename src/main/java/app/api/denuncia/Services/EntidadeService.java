package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.EntidadeModel;

public interface EntidadeService {

    Response listar();

    Response get_by_id(int id);

    Response getEntidadeByTipo(String tipo);

    Boolean existsEntidade(EntidadeModel ent);

    Response alterarEstado(int id, int estado);

    Boolean existsByIdAndEstado(int id, int estado);

    Response adicionar_atualizar(EntidadeModel entidade);
}
