package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.MenuModel;

public interface MenuService {

    Response listar();

    Response get_by_id(int id);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(MenuModel menu);
}
