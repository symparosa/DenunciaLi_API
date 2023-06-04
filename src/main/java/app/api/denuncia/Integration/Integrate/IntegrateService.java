package app.api.denuncia.Integration.Integrate;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.DenunciaModel;

public interface IntegrateService {

    void Integracao(DenunciaModel denunciaModel);

    Response reprocessamentoManualDenuncia(int id);
}
