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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "dn_t_entidade_tipo_crime")
public class EntidadeTipoCrimeModel implements Serializable{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "entidade_fk")
    private EntidadeModel entidade;

    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipo_crime;

    private int estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public EntidadeTipoCrimeModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public EntidadeModel getEntidade() {
        return entidade;
    }

    public void setEntidade(EntidadeModel entidade) {
        this.entidade = entidade;
    }

    public DominioModel getTipo_crime() {
        return tipo_crime;
    }

    public void setTipo_crime(DominioModel tipo_crime) {
        this.tipo_crime = tipo_crime;
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
