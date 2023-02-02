package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_queixa")
public class QueixaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Lob
    private String descricao;

    private Date data_ocorrencia;

    private String descricao_endereco;

    private String porta;

    private String rua;

    private String localizacao_mapa;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @ManyToOne
    @JoinColumn(name = "grau_parentesco_fk")
    private DominioModel grau_parentesco;

    @ManyToOne
    @JoinColumn(name = "tipo_queixa_fk")
    private DominioModel tipo_queixa;

    @OneToMany(targetEntity = ArquivoModel.class, mappedBy = "queixa", cascade = CascadeType.ALL)
    private List<ArquivoModel> Arquivos = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "tipo_crime_fk")
    private DominioModel tipo_crime;

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public QueixaModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData_ocorrencia() {
        return data_ocorrencia;
    }

    public void setData_ocorrencia(Date data_ocorrencia) {
        this.data_ocorrencia = data_ocorrencia;
    }

    public String getDescricao_endereco() {
        return descricao_endereco;
    }

    public void setDescricao_endereco(String descricao_endereco) {
        this.descricao_endereco = descricao_endereco;
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

    public String getLocalizacao_mapa() {
        return localizacao_mapa;
    }

    public void setLocalizacao_mapa(String localizacao_mapa) {
        this.localizacao_mapa = localizacao_mapa;
    }

    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
    }

    public DominioModel getGrau_parentesco() {
        return grau_parentesco;
    }

    public void setGrau_parentesco(DominioModel grau_parentesco) {
        this.grau_parentesco = grau_parentesco;
    }

    public DominioModel getTipo_queixa() {
        return tipo_queixa;
    }

    public void setTipo_queixa(DominioModel tipo_queixa) {
        this.tipo_queixa = tipo_queixa;
    }

    public List<ArquivoModel> getArquivos() {
        return Arquivos;
    }

    public void setArquivos(List<ArquivoModel> arquivos) {
        Arquivos = arquivos;
    }

    public DominioModel getTipo_crime() {
        return tipo_crime;
    }

    public void setTipo_crime(DominioModel tipo_crime) {
        this.tipo_crime = tipo_crime;
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
