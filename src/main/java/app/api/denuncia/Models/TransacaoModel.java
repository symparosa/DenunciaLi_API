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
    private int id;

    @ManyToOne
    @JoinColumn(name = "botao_fk")
    private BotaoModel botao;

    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipo_utilizador;

    private int estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public TransacaoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BotaoModel getBotao() {
        return botao;
    }

    public void setBotao(BotaoModel botao) {
        this.botao = botao;
    }

    public DominioModel getTipo_utilizador() {
        return tipo_utilizador;
    }

    public void setTipo_utilizador(DominioModel tipo_utilizador) {
        this.tipo_utilizador = tipo_utilizador;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
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
}
