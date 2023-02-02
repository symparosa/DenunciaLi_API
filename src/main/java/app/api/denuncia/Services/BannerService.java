package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response.ResponseDto;
import app.api.denuncia.Models.BannerModel;

public interface BannerService {
    
    ResponseDto adicionar_atualizar(BannerModel banner);

    ResponseDto alterarEstado(int id, int estado);

    ResponseDto listar();
}
