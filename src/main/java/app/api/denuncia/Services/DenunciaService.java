package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.DenunciaOutputDto;
import app.api.denuncia.Models.ResponseModel;

public interface DenunciaService {

    ResponseModel adicionarDenuncia(String denuncia);

    List<DenunciaOutputDto> listarDenunciasByUserId(int id);

    // ResponseModel getDenunciaById(int id);
}
