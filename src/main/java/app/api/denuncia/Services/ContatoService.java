package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Models.ContatoModel;
import app.api.denuncia.Models.ResponseModel;

public interface ContatoService {

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel getInfoByIdObjeto(int id_obj, String tipo_obj);

    ResponseModel adicionar_atualizar(List<ContatoModel> contatoModels);
}
