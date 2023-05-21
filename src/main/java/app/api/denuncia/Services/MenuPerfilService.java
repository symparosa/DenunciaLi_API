package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;

public interface MenuPerfilService {

    Response alterarPermissao(int id_menu, int id_perfil, int estado);
}
