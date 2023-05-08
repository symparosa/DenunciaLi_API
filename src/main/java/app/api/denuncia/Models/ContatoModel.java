package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;
import jakarta.persistence.Column;
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
@Table(name = "dn_t_contato")
public class ContatoModel implements Serializable {

    @Schema(description = "O identificador (ID) do contato")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) do objeto")
    @Column(nullable = false, name = "id_objeto")
    private Integer idObjeto;

    @Schema(description = "O tipo do objeto")
    @Column(nullable = false, name = "tipo_objeto")
    private String tipoObjeto;

    @Schema(description = "O valor do contato")
    @Column(nullable = false)
    private String valor;

    @Schema(description = "O tipo do contato")
    @ManyToOne
    @JoinColumn(name = "tipo_contato_fk")
    private DominioModel tipoContato;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do contato", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do contato", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do contato", hidden = true)
    private Date data_atualizacao;
}
