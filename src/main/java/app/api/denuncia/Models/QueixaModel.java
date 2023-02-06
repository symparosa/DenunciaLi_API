package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_queixa")
public class QueixaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String descricao;

    private Date data_ocorrencia;

    private String descricao_endereco;

    private String referencia_morada;

    private String codigo_postal;

    private String localizacao_mapa;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @ManyToOne
    @JoinColumn(name = "grau_parentesco_fk")
    private DominioModel grau_parentesco;

    @ManyToOne
    @JoinColumn(name = "tipo_queixa_fk")
    private DominioModel tipo_queixa;

    @OneToMany(targetEntity = ArquivoModel.class, mappedBy = "queixa", cascade = CascadeType.ALL)
    private List<ArquivoModel> Arquivos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipo_crime;

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
