package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
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
@Table(name = "dn_t_entidade")
public class EntidadeModel implements Serializable {

    @Schema(description = "O identificador (ID) da entidade")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O nome da entidade")
    @Column(nullable = false)
    private String nome;

    @Schema(description = "A referencia_morada da entidade")
    private String referencia_morada;

    @Schema(description = "A codigo_postal da entidade")
    private String codigo_postal;

    @Schema(description = "A imagem da entidade")
    @Lob
    @Column(nullable = false)
    private String imagem;

    @Schema(description = "O sigla da entidade")
    @Column(nullable = false, unique = true)
    private String sigla;

    @Schema(description = "A descrição da entidade")
    @Lob
    private String descricao;

    @Schema(description = "A localização da entidade")
    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "O tipo de entidade")
    @ManyToOne
    @JoinColumn(name = "tipo_entidade_fk")
    private DominioModel tipo_entidade;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da entidade", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da entidade", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da entidade", hidden = true)
    private Date data_atualizacao;
}
