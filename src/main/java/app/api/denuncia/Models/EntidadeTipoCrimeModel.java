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

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_entidade_tipo_crime")
public class EntidadeTipoCrimeModel implements Serializable{
    
    @Schema(description = "O identificador (ID) da entidade e tipo de crime")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) da entidade")
    @ManyToOne
    @JoinColumn(name = "entidade_fk")
    private EntidadeModel entidade;

    @Schema(description = "O identificador (ID) do tipo de crime")
    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipoCrime;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado da entidade e tipo de crime", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da entidade e tipo de crime", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da entidade e tipo de crime", hidden = true)
    private Date data_atualizacao;

    public EntidadeTipoCrimeModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public EntidadeModel getEntidade() {
        return entidade;
    }

    public void setEntidade(EntidadeModel entidade) {
        this.entidade = entidade;
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

    public DominioModel getTipoCrime() {
        return tipoCrime;
    }

    public void setTipoCrime(DominioModel tipoCrime) {
        this.tipoCrime = tipoCrime;
    }
}
