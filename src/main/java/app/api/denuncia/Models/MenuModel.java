package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;
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
@Table(name = "dn_t_menu")
public class MenuModel implements Serializable {

    @Schema(description = "O identificador (ID) do menu")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O código do menu")
    @Column(nullable = false, unique = true)
    private String codigo;

    @Schema(description = "O título do menu")
    @Column(nullable = false)
    private String titulo;

    @Schema(description = "A visibilidade do menu")
    @Column(nullable = false)
    private Integer visibilidade;

    @Schema(description = "O identificador (ID) do menu pai")
    private Integer id_menu_pai;

    @Schema(description = "O icon do menu")
    @Lob
    private String menu_icon;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do menu", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do menu", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do menu", hidden = true)
    private Date data_atualizacao;
}
