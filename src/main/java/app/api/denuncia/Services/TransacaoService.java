package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;

public interface TransacaoService {
    
    ResponseDto alterarPermissao(int id_botao, int id_perfil, int estado);
}
