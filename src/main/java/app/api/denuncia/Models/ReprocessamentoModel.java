package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_reprocessamento")
public class ReprocessamentoModel implements Serializable {

    @Schema(description = "O identificador (ID) do reprocessamento")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) da denuncia")
    @ManyToOne
    @JoinColumn(name = "denuncia_fk")
    private DenunciaModel denuncia;

    @Schema(description = "O identificador (ID) do tipo de reprocessamento")
    @ManyToOne
    @JoinColumn(name = "tipo_reprocessamento_fk")
    private DominioModel tipo_reprocessamento;

    @Lob
    @Schema(description = "O body do email")
    private String email_body;

    @Schema(description = "O email do utilizador")
    private String email_user;

    @Schema(description = "O subject do email")
    private String email_subject;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do reprocessamento", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do reprocessamento", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização do reprocessamento", hidden = true)
    private LocalDateTime data_atualizacao;
}
