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

    public TransacaoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public BotaoModel getBotao() {
        return botao;
    }

    public void setBotao(BotaoModel botao) {
        this.botao = botao;
    }

    public Integer getLast_user_change() {
        return last_user_change;
    }

    public void setLast_user_change(Integer last_user_change) {
        this.last_user_change = last_user_change;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public Date getData_criacao() {
        return data_criacao;
    }

    public void setData_criacao(Date data_criacao) {
        this.data_criacao = data_criacao;
    }

    public Date getData_atualizacao() {
        return data_atualizacao;
    }

    public void setData_atualizacao(Date data_atualizacao) {
        this.data_atualizacao = data_atualizacao;
    }

    public DominioModel getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(DominioModel tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }
}
