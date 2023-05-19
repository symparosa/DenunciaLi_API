package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.BannerModel;

public interface BannerService {
    
    Response adicionar_atualizar(BannerModel banner);

    Response alterarEstado(int id, int estado);

    Response listar();

    Response get_by_id(int id);
}
