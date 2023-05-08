package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_queixa")
public class QueixaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String descricao;

    private LocalDateTime data_ocorrencia;

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
    private List<ArquivoModel> arquivos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipo_crime;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da queixa", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da queixa", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da queixa", hidden = true)
    private Date data_atualizacao;
}
