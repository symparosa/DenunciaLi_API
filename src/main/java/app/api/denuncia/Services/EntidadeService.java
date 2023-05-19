package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.EntidadeModel;

public interface EntidadeService {

    Response listar();

    Response get_by_id(int id);

    Boolean existsEntidade(EntidadeModel ent);

    Response getEntidadeByTipo(String tipo);

    Boolean existsByIdAndEstado(int id, int estado);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(EntidadeModel entidade);
}
