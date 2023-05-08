package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Models.DominioModel;
import app.api.denuncia.Models.ResponseModel;

public interface DominioService {

    ResponseModel listar();

    ResponseModel get_by_id(int id);

    ResponseModel getDominio(String dominio);

    ResponseModel alterarEstado(int id, int estado);

    Boolean existsTipo(DominioModel tipo, String dom);

    DominioModel findByDominioAndValor(String dom, String tipo);

    ResponseModel adicionar_atualizar(List<DominioModel> dominio);
}
