package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_arquivo")
public class ArquivoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String arquivo;

    @ManyToOne
    @JoinColumn(name = "tipo_arquivo_fk")
    private DominioModel tipo_arquivo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "queixa_fk")
    private QueixaModel queixa;

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    @Column
    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public ArquivoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

    public DominioModel getTipo_arquivo() {
        return tipo_arquivo;
    }

    public void setTipo_arquivo(DominioModel tipo_arquivo) {
        this.tipo_arquivo = tipo_arquivo;
    }

    public QueixaModel getQueixa() {
        return queixa;
    }

    public void setQueixa(QueixaModel queixa) {
        this.queixa = queixa;
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
}
