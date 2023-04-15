package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ResponseModel;

public interface DominioService {

    ResponseModel adicionar_atualizar(List<DominioModel> dominio);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();

    ResponseModel getDominio(String dominio);

    Boolean existsTipo(DominioModel tipo, String dom);

    ResponseModel get_by_id(int id);
}
