package app.api.denuncia.Services;

import java.time.LocalDate;

import app.api.denuncia.Dto.Response;
import app.api.denuncia.Models.ReprocessamentoModel;

public interface ReprocessamentoService {

    Response alterarEstado(int id, int estado);

    Response reprocessamentoManualEmail(int id);

    ReprocessamentoModel saveReprocessamento(ReprocessamentoModel repro);

    void saveReprocessamentoEmail(String recipient, String msgBody, String subject,int user_id);

    Response filtroReprocessamentoEmail(LocalDate data_inicio, LocalDate data_fim, Integer estado);
}
