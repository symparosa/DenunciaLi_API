package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.DominioModel;

public interface DominioService {

    Response listar();

    Response get_by_id(int id);

    DominioModel findbyid(Integer id);

    Response getDominio(String dominio);

    Response alterarEstado(int id, int estado);

    Boolean existsTipo(DominioModel tipo, String dom);

    Response adicionar_atualizar(List<DominioModel> dominio);

    DominioModel findByDominioAndValor(String dom, String tipo);
}
