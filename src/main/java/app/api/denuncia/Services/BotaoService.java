package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BotaoModel;

public interface BotaoService {
    
    ResponseDto adicionar_atualizar(BotaoModel botao);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();
}
