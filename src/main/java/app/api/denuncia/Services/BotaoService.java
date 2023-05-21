package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.BotaoModel;

public interface BotaoService {

    Response listar();

    Response get_by_id(int id);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(BotaoModel botao);
}
