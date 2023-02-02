package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.MenuModel;

public interface MenuService {

    ResponseDto adicionar_atualizar(MenuModel menu);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();
}
