package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface MenuPerfilService {
    
    ResponseDto alterarPermissao(int id_menu, int id_perfil, int estado);
}
