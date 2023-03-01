package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_informacao_legal")
public class InformacaoLegalModel implements Serializable {

    @Schema(description = "O identificador (ID) da informação legal")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O título da informação legal")
    private String titulo;

    @Schema(description = "A descrição da informação legal")
    @Lob
    private String descricao;

    @Schema(description = "O identificador (ID) do tipo de informação legal")
    @ManyToOne
    @JoinColumn(name = "tipo_informacao_legal_fk")
    private DominioModel tipoInformacaoLegal;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "A data de atualização da informação legal", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da informação legal", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da informação legal", hidden = true)
    private Date data_atualizacao;
}
