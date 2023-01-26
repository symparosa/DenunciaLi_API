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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "dn_t_entidade")
public class EntidadeModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true, nullable = false)
    private String nome;

    private String porta;

    private String rua;

    @Column(nullable = false)
    private String imagem;

    private String sigla;

    @Lob
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @ManyToOne
    @JoinColumn(name = "tipo_entidade_fk")
    private DominioModel tipo_entidade;

    @OneToMany(targetEntity = EntidadeTipoCrimeModel.class, mappedBy = "entidade", cascade = CascadeType.ALL)
    private List<EntidadeTipoCrimeModel> tipos_crimes = new ArrayList<>();
    
    private int estado;

    private Date data_criacao;

    private Date data_atualizacao;

    public EntidadeModel() {
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

    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
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

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public DominioModel getTipo_entidade() {
        return tipo_entidade;
    }

    public void setTipo_entidade(DominioModel tipo_entidade) {
        this.tipo_entidade = tipo_entidade;
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

    public List<EntidadeTipoCrimeModel> getTipos_crimes() {
        return tipos_crimes;
    }

    public void setTipos_crimes(List<EntidadeTipoCrimeModel> tipos_crimes) {
        this.tipos_crimes = tipos_crimes;
    }
}
