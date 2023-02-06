package app.api.denuncia.Services;

import app.api.denuncia.Models.ResponseModel;

public interface MenuPerfilService {
    
    ResponseModel alterarPermissao(int id_menu, int id_perfil, int estado);
}
