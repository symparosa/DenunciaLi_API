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
@Table(name = "dn_t_botao")
public class BotaoModel implements Serializable {

    @Schema(description = "O identificador (ID) do botão")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O código do botão")
    @Column(nullable = false)
    private String codigo;

    @Schema(description = "A descrição do botão")
    @Lob
    private String descricao;

    @Schema(description = "O icon do botão")
    @Lob
    private String botao_icon;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do botão", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do botão", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do botão", hidden = true)
    private Date data_atualizacao;
}
