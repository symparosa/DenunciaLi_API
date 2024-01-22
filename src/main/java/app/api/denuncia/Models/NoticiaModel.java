package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_noticia")
public class NoticiaModel implements Serializable {

    @Schema(description = "O identificador (ID) da notícia")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O título da notícia")
    private String titulo;

    @Schema(description = "A imagem de capa da notícia")
    @Lob
    private String imagem_capa;

    @Schema(description = "A introdução da notícia")
    @Lob
    private String introducao;

    @Schema(description = "A descrição da notícia")
    @Lob
    private String descricao;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da notícia", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da notícia", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização da notícia", hidden = true)
    private LocalDateTime data_atualizacao;
}
