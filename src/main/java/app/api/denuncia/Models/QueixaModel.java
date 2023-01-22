package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "queixa")
public class QueixaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column
    private String descricao;

    @Column
    private String grauParentesco;

    @Column
    private Date dataOcorrencia;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Column
    private String descricaoEndereco;

    @Column
    private String porta;

    @Column
    private String rua;

    @ManyToOne
    @JoinColumn(name = "tipoQueixa_fk")
    private Tipo_QueixaModel Tipo_Queixa;

    @OneToMany(targetEntity = ArquivoModel.class, mappedBy = "queixa", cascade = CascadeType.ALL)
    private List<ArquivoModel> Arquivos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipoCrime_fk")
    private Tipo_CrimeModel tipoCrime;

    @Column
    private String localizacaoGps_Map;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public QueixaModel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getGrauParentesco() {
        return grauParentesco;
    }

    public void setGrauParentesco(String grauParentesco) {
        this.grauParentesco = grauParentesco;
    }

    public Date getDataOcorrencia() {
        return dataOcorrencia;
    }

    public void setDataOcorrencia(Date dataOcorrencia) {
        this.dataOcorrencia = dataOcorrencia;
    }

    public String getPorta() {
        return porta;
    }

    public void setPorta(String porta) {
        this.porta = porta;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
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

    public Tipo_QueixaModel getTipo_Queixa() {
        return Tipo_Queixa;
    }

    public void setTipo_Queixa(Tipo_QueixaModel tipo_Queixa) {
        Tipo_Queixa = tipo_Queixa;
    }

    public List<ArquivoModel> getArquivos() {
        return Arquivos;
    }

    public void setArquivos(List<ArquivoModel> arquivos) {
        Arquivos = arquivos;
    }

    public Tipo_CrimeModel getTipoCrime() {
        return tipoCrime;
    }

    public void setTipoCrime(Tipo_CrimeModel tipoCrime) {
        this.tipoCrime = tipoCrime;
    }

    public String getDescricaoEndereco() {
        return descricaoEndereco;
    }

    public void setDescricaoEndereco(String descricaoEndereco) {
        this.descricaoEndereco = descricaoEndereco;
    }

    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
    }

    public String getLocalizacaoGps_Map() {
        return localizacaoGps_Map;
    }

    public void setLocalizacaoGps_Map(String localizacaoGps_Map) {
        this.localizacaoGps_Map = localizacaoGps_Map;
    }
}
