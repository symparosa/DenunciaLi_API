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
@Table(name = "dn_t_banner")
public class BannerModel implements Serializable {

    @Schema(description = "O identificador (ID) do banner")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "A imagem do banner")
    @Lob
    @Column(nullable = false)
    private String imagem;

    @Schema(description = "O título do banner")
    @Column(nullable = false)
    private String titulo;

    @Schema(description = "A descrição do banner")
    @Lob
    private String descricao;

    @Schema(description = "A url do banner")
    @Column(nullable = false)
    private String url;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do banner", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do banner", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do banner", hidden = true)
    private Date data_atualizacao;
}
