package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_transacao")
public class TransacaoModel implements Serializable {

    @Schema(description = "O identificador (ID) da transação")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) do botão")
    @ManyToOne
    @JoinColumn(name = "botao_fk")
    private BotaoModel botao;

    @Schema(description = "O identificador (ID) do tipo de utilizador")
    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipoUtilizador;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da transação", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da transação", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização da transação", hidden = true)
    private LocalDateTime data_atualizacao;
}
