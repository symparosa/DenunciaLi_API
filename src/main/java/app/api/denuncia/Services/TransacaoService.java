package app.api.denuncia.Services;

import app.api.denuncia.Models.ResponseModel;

public interface TransacaoService {
    
    ResponseModel alterarPermissao(int id_botao, int id_perfil, int estado);
}
