package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "denuncia")
public class DenunciaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "queixa_fk")
    private QueixaModel queixa;

    @ManyToOne
    @JoinColumn(name = "utilizador_fk")
    private UtilizadorModel utilizador;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public DenunciaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public UtilizadorModel getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(UtilizadorModel utilizador) {
        this.utilizador = utilizador;
    }
}
