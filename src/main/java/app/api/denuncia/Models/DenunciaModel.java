package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
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

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
