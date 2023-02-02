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

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_contato")
public class ContatoModel implements Serializable {

    @Schema(description = "O identificador (ID) do contato")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O identificador (ID) do objeto")
    @Column(nullable = false)
    private Integer idObjeto;

    @Schema(description = "O tipo do objeto")
    @Column(nullable = false)
    private String tipoObjeto;

    @Schema(description = "O valor do contato")
    @Column(unique = true, nullable = false)
    private String valor;

    @Schema(description = "O tipo do contato")
    @ManyToOne
    @JoinColumn(name = "tipo_contato_fk")
    private DominioModel tipo_contato;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do contato", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do contato", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do contato", hidden = true)
    private Date data_atualizacao;

    public ContatoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public Integer getIdObjeto() {
        return idObjeto;
    }

    public void setIdObjeto(Integer idObjeto) {
        this.idObjeto = idObjeto;
    }

    public String getTipoObjeto() {
        return tipoObjeto;
    }

    public void setTipoObjeto(String tipoObjeto) {
        this.tipoObjeto = tipoObjeto;
    }
}
