package app.api.denuncia.Services;

import java.time.LocalDate;
import java.util.List;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.DenunciaModel;
import app.api.denuncia.Models.ReprocessamentoModel;

public interface ReprocessamentoService {

    List<Integer> getIdEmailNaoRepro();

    List<Integer> getIdDenunciaNaoRepro();

    Response alterarEstado(int id, int estado);

    Response reprocessamentoManualEmail(int id);

    ReprocessamentoModel getReprocessamentoById(int id);

    void saveReprocessamentoDenuncia(DenunciaModel denuncia);

    void saveReprocessamentoEmail(String recipient, String msgBody, String subject, int user_id);

    Response filtroReprocessamentoEmail(LocalDate data_inicio, LocalDate data_fim, Integer estado);

    Response filtroReprocessamentoDenuncia(LocalDate data_inicio, LocalDate data_fim, Integer estado);
}
