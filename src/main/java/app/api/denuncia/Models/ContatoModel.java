package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "dn_t_contato")
public class ContatoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int id_objeto;

    @Column(nullable = false)
    private String tipo_objeto;

    @Column(unique = true, nullable = false)
    private String valor;

    @ManyToOne
    @JoinColumn(name = "tipo_contato_fk")
    private DominioModel tipo_contato;

    private int estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public ContatoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_objeto() {
        return id_objeto;
    }

    public void setId_objeto(int id_objeto) {
        this.id_objeto = id_objeto;
    }

    public String getTipo_objeto() {
        return tipo_objeto;
    }

    public void setTipo_objeto(String tipo_objeto) {
        this.tipo_objeto = tipo_objeto;
    }

    public String getValor() {
        return valor;
    }

    public void setValor(String valor) {
        this.valor = valor;
    }

    public DominioModel getTipo_contato() {
        return tipo_contato;
    }

    public void setTipo_contato(DominioModel tipo_contato) {
        this.tipo_contato = tipo_contato;
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
