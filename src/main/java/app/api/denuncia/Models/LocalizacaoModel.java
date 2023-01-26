package app.api.denuncia.Models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;
@Entity
@Table(name = "dn_t_localizacao")
public class LocalizacaoModel implements Serializable {

    @Id
    @Column(nullable = false)
    private String id;

    @Column(nullable = false)
    private String nome;

    private String pais;

    private String ilha;

    private String concelho;

    private String freguesia;

    private String zona;

    private String nome_norm;

    @Column(nullable = false)
    private String nivel_detalhe;

    @Column(nullable = false)
    private String colmatch;

    @Lob
    @Column(nullable = false)
    private String polygonarea;

    public LocalizacaoModel() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public String getIlha() {
        return ilha;
    }

    public void setIlha(String ilha) {
        this.ilha = ilha;
    }

    public String getConcelho() {
        return concelho;
    }

    public void setConcelho(String concelho) {
        this.concelho = concelho;
    }

    public String getFreguesia() {
        return freguesia;
    }

    public void setFreguesia(String freguesia) {
        this.freguesia = freguesia;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getNome_norm() {
        return nome_norm;
    }

    public void setNome_norm(String nome_norm) {
        this.nome_norm = nome_norm;
    }

    public String getNivel_detalhe() {
        return nivel_detalhe;
    }

    public void setNivel_detalhe(String nivel_detalhe) {
        this.nivel_detalhe = nivel_detalhe;
    }

    public String getColmatch() {
        return colmatch;
    }

    public void setColmatch(String colmatch) {
        this.colmatch = colmatch;
    }

    public String getPolygonarea() {
        return polygonarea;
    }

    public void setPolygonarea(String polygonarea) {
        this.polygonarea = polygonarea;
    }
}
