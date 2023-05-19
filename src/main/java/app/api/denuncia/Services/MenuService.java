package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.MenuModel;

public interface MenuService {

    Response adicionar_atualizar(MenuModel menu);

    Response alterarEstado(int id, int estado);

    Response listar();

    Response get_by_id(int id);
}
