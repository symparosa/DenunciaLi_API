package app.api.denuncia.Services;

import app.api.denuncia.Dto.Response;

public interface DenunciaService {

    Response listar_ocorrencias();

    Response adicionarDenuncia(String denuncia);
}
