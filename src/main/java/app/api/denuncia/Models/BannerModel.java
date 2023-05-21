package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização do banner", hidden = true)
    private LocalDateTime data_atualizacao;
}
