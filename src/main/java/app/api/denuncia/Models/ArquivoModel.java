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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "arquivo")
public class ArquivoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    @Lob
    private String arquivo;

    @ManyToOne
    @JoinColumn(name = "tipoArquivo_fk")
    @NotNull()
    private Tipo_ArquivoModel tipoArquivo;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "queixa_fk")
    private QueixaModel queixa;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public ArquivoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Tipo_ArquivoModel getTipoArquivo() {
        return tipoArquivo;
    }

    public void setTipoArquivo(Tipo_ArquivoModel tipoArquivo) {
        this.tipoArquivo = tipoArquivo;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public Date getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Date dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Date getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(Date dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }

    public QueixaModel getQueixa() {
        return queixa;
    }

    public void setQueixa(QueixaModel queixa) {
        this.queixa = queixa;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }
}
