package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_transacao")
public class TransacaoModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "botao_fk")
    private BotaoModel botao;

    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipoUtilizador;

    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
