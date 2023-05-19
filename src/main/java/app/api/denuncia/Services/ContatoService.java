package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.ContatoModel;

public interface ContatoService {

    Response alterarEstado(int id, int estado);

    Response getInfoByIdObjeto(int id_obj, String tipo_obj);

    Response adicionar_atualizar(List<ContatoModel> contatoModels);
}
