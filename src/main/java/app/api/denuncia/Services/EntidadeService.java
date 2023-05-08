package app.api.denuncia.Services;

import app.api.denuncia.Models.EntidadeModel;
import app.api.denuncia.Models.ResponseModel;

public interface EntidadeService {

    ResponseModel listar();

    ResponseModel get_by_id(int id);

    Boolean existsEntidade(EntidadeModel ent);

    ResponseModel getEntidadeByTipo(String tipo);

    Boolean existsByIdAndEstado(int id, int estado);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel adicionar_atualizar(EntidadeModel entidade);
}
