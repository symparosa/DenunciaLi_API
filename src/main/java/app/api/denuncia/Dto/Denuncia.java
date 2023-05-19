package app.api.denuncia.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Denuncia {
    private LocalDateTime data_criacao;
    private int estado;
    private String codigo_postal;
    private LocalDateTime data_ocorrencia;
    private String descricao;
    private String localizacao_mapa;
    private String referencia_morada;
    private String localizacao_nome;
    private String localizacao_nome_norm;
    private String grau_parentesco;
    private String tipo_crime;
    private String tipo_queixa;
}
