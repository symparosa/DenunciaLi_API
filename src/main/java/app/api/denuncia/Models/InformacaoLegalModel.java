package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_informacao_legal")
public class InformacaoLegalModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String titulo;

    @Lob
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "tipo_informacao_legal_fk")
    private DominioModel tipoInformacaoLegal;

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
