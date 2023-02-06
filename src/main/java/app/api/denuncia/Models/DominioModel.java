package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_dominio")
public class DominioModel implements Serializable {

    @Schema(description = "O identificador (ID) do domínio")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O domínio")
    @Column(nullable = false)
    private String dominio;

    @Schema(description = "O valor do domínio")
    @Column(nullable = false)
    private String valor;

    @Schema(description = "O descrição do domínio")
    @Lob
    private String descricao;

    @Schema(description = "O estado do domínio", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do domínio", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do domínio", hidden = true)
    private Date data_atualizacao;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;
}
