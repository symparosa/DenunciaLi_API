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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "Localizacao")
public class LocalizacaoModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    @NotNull()
    private String nome;

    @ManyToOne
    @JoinColumn(name = "tipoNivel_fk")
    private Tipo_NivelModel Tipo_Nivel;

    @Column
    private String latitude;

    @Column
    private String longitude;

    @OneToOne
    @JoinColumn(name = "selfId")
    private LocalizacaoModel selfId;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public LocalizacaoModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Tipo_NivelModel getTipo_Nivel() {
        return Tipo_Nivel;
    }

    public void setTipo_Nivel(Tipo_NivelModel tipo_Nivel) {
        Tipo_Nivel = tipo_Nivel;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public LocalizacaoModel getSelfId() {
        return selfId;
    }

    public void setSelfId(LocalizacaoModel selfId) {
        this.selfId = selfId;
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
}
