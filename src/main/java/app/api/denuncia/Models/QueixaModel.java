package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
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

    @Schema(description = "O identificador (ID) da queixa")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "A descrição da queixa")
    @Lob
    private String descricao;

    @Schema(description = "A data de ocorrência da queixa")
    private LocalDateTime data_ocorrencia;

    @Schema(description = "A descrição de endereço da queixa")
    private String referencia_morada;

    @Schema(description = "O codigo postal da queixa")
    private String codigo_postal;

    @Schema(description = "A localização no mapa da queixa")
    private String localizacao_mapa;

    @Schema(description = "O identificador (ID) da localização")
    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "O identificador (ID) do grau de parentesco")
    @ManyToOne
    @JoinColumn(name = "grau_parentesco_fk")
    private DominioModel grau_parentesco;

    @Schema(description = "O identificador (ID) do tipo de queixa")
    @ManyToOne
    @JoinColumn(name = "tipo_queixa_fk")
    private DominioModel tipo_queixa;

    @Schema(description = "A lista de arquivos")
    @OneToMany(targetEntity = ArquivoModel.class, mappedBy = "queixa", cascade = CascadeType.ALL)
    private List<ArquivoModel> arquivos = new ArrayList<>();

    @Schema(description = "O identificador (ID) do tipo de crime")
    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipo_crime;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da queixa", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da queixa", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização da queixa", hidden = true)
    private LocalDateTime data_atualizacao;
}
