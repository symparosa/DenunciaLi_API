package app.api.denuncia.Services;

import app.api.denuncia.Models.MenuModel;
import app.api.denuncia.Models.ResponseModel;

public interface MenuService {

    ResponseModel adicionar_atualizar(MenuModel menu);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();
}
