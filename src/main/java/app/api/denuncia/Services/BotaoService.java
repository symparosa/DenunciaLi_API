package app.api.denuncia.Services;

import app.api.denuncia.Models.BotaoModel;
import app.api.denuncia.Models.ResponseModel;

public interface BotaoService {
    
    ResponseModel adicionar_atualizar(BotaoModel botao);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();
}
