package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.BannerModel;

public interface BannerService {

    Response listar();

    Response get_by_id(int id);

    Response alterarEstado(int id, int estado);

    Response adicionar_atualizar(BannerModel banner);
}
