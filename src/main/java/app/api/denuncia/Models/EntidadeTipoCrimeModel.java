package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "dn_t_entidade_tipo_crime")
public class EntidadeTipoCrimeModel implements Serializable {

    @Schema(description = "O identificador (ID) da entidade e tipo de crime")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) da entidade")
    @ManyToOne
    @JoinColumn(name = "entidade_fk")
    private EntidadeModel entidade;

    @Schema(description = "O identificador (ID) do tipo de crime")
    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipoCrime;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da entidade e tipo de crime", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da entidade e tipo de crime", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da entidade e tipo de crime", hidden = true)
    private Date data_atualizacao;
}
