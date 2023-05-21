package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_menu_perfil")
public class MenuPerfilModel implements Serializable {

    @Schema(description = "O identificador (ID) do menu perfil")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) do menu")
    @ManyToOne
    @JoinColumn(name = "menu_fk")
    private MenuModel menu;

    @Schema(description = "O identificador (ID) do perfil")
    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipoUtilizador;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do menu perfil", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do menu perfil", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização do menu perfil", hidden = true)
    private LocalDateTime data_atualizacao;
}
