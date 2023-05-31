package app.api.denuncia.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReprocessamentoEmail {
    private Integer id;
    private LocalDateTime data_criacao;
    private String email_body;
    private String email_subject;
    private String email_user;
    private Integer estado;
    private String reprocessamento;
}
