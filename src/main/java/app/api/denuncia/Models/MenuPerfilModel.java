package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

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

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "menu_fk")
    private MenuModel menu;

    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipoUtilizador;

    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
