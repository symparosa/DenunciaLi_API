package app.api.denuncia.Integration.Dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Denuncia {

    private LocalDateTime data_denuncia;
    private LocalDateTime data_ocorrencia;
    private String descricao_denuncia;
    private String local_mapa;
    private String referencia_local;
    private String grau_parentesco;
    private String tipo_crime;
    private String tipo_queixa;
    private String zona_lugar;
    private String codigo_postal;
    private Denunciante denunciante;
    private List<Anexos> anexos;
}
