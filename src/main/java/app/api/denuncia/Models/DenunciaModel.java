package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.CascadeType;
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
@Table(name = "dn_t_denuncia")
public class DenunciaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "queixa_fk")
    private QueixaModel queixa;

    @ManyToOne
    @JoinColumn(name = "denunciante_fk")
    private DenuncianteModel denunciante;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da denuncia", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da denuncia", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da denuncia", hidden = true)
    private Date data_atualizacao;
}
