package app.api.denuncia.Services;

import java.time.LocalDate;

import app.api.denuncia.Dto.Response;

public interface AuditoriaService {

    Response filtroAuditoria(String accao, String tipo_objeto, LocalDate data_inicio, LocalDate data_fim);
}
