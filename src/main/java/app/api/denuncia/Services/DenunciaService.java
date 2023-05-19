package app.api.denuncia.Services;

import java.util.List;

import app.api.denuncia.Dto.Denuncia;
import app.api.denuncia.Dto.Response;

public interface DenunciaService {

    Response adicionarDenuncia(String denuncia);

    List<Denuncia> listarDenunciasByUserId(int denu);
}
