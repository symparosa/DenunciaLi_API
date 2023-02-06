package app.api.denuncia.Services;

import app.api.denuncia.Models.BannerModel;
import app.api.denuncia.Models.ResponseModel;

public interface BannerService {
    
    ResponseModel adicionar_atualizar(BannerModel banner);

    ResponseModel alterarEstado(int id, int estado);

    ResponseModel listar();
}
