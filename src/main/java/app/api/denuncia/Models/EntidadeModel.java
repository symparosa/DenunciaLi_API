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


import io.swagger.v3.oas.annotations.media.Schema;

@Entity
@Table(name = "dn_t_entidade")
public class EntidadeModel implements Serializable {

    @Schema(description = "O identificador (ID) da entidade")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O nome da entidade")
    @Column(unique = true, nullable = false)
    private String nome;

    @Schema(description = "A porta da entidade")
    private String porta;

    @Schema(description = "A rua da entidade")
    private String rua;

    @Schema(description = "A imagem da entidade")
    @Lob
    @Column(nullable = false)
    private String imagem;

    @Schema(description = "O sigla da entidade")
    private String sigla;

    @Schema(description = "A descrição da entidade")
    @Lob
    private String descricao;

    @Schema(description = "A localização da entidade")
    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "O tipo de entidade")
    @ManyToOne
    @JoinColumn(name = "tipo_entidade_fk")
    private DominioModel tipo_entidade;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;
    
    @Schema(description = "O estado da entidade", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação da entidade", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização da entidade", hidden = true)
    private Date data_atualizacao;

    public EntidadeModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
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

    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
    }

    public DominioModel getTipo_entidade() {
        return tipo_entidade;
    }

    public void setTipo_entidade(DominioModel tipo_entidade) {
        this.tipo_entidade = tipo_entidade;
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
