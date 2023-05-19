package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;

public interface TransacaoService {
    
    Response alterarPermissao(int id_botao, int id_perfil, int estado);
}
