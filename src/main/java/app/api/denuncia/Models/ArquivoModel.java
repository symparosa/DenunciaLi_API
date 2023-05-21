package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_arquivo")
public class ArquivoModel implements Serializable {

    @Schema(description = "O identificador (ID) do arquivo")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O arquivo")
    @Lob
    private String arquivo;

    @Schema(description = "O tipo de arquivo")
    @ManyToOne
    @JoinColumn(name = "tipo_arquivo_fk")
    private DominioModel tipo_arquivo;

    @Schema(description = "O identificador (ID) da queixa")
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "queixa_fk")
    private QueixaModel queixa;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do arquivo", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do arquivo", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização do arquivo", hidden = true)
    private LocalDateTime data_atualizacao;
}
