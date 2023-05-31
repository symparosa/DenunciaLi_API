package app.api.denuncia.Services;

import java.time.LocalDate;

import app.api.denuncia.Dto.Response;

public interface DenunciaService {

    Response listar_ocorrencias();

    Response adicionarDenuncia(String denuncia);

    Response filtroDenuncia(Integer TipoQueixa, Integer TipoCrime, LocalDate data_inicio, LocalDate data_fim,
            Integer idade_inicio,
            Integer idade_fim, String genero, Integer concelho);
}
