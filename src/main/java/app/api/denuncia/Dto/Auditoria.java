package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import jakarta.persistence.Lob;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Auditoria {
    private String accao;
    private LocalDateTime data_criacao;
    private Integer estado;
    private Integer id_objeto;
    private Integer id_utilizador;
    private String tipo_objeto;
    @Lob
    private String valor_atual;
    @Lob
    private String valor_novo;
}
