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
@Table(name = "dn_t_utilizador_backoffice")
public class UtilizadorBackofficeModel implements Serializable {

    @Schema(description = "O identificador (ID) do utilizador")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O username do utilizador")
    @Column(unique = true, nullable = false)
    private String username;

    @Schema(description = "O password do utilizador")
    @Column(nullable = false)
    private String password;

    @Schema(description = "O nome do utilizador")
    @Column(nullable = false)
    private String nome;

    @Schema(description = "A foto de perfil do utilizador")
    @Lob
    private String foto_perfil;

    @Schema(description = "O identificador (ID) da localização")
    @ManyToOne()
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "O identificador (ID) do tipo de utilizador")
    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipo_utilizador;

    @Schema(description = "O identificador (ID) da entidade")
    @ManyToOne
    @JoinColumn(name = "entidade_fk")
    private EntidadeModel entidade;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Schema(description = "O estado do utilizador", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do utilizador", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do utilizador", hidden = true)
    private Date data_atualizacao;

    public UtilizadorBackofficeModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }

    public LocalizacaoModel getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(LocalizacaoModel localizacao) {
        this.localizacao = localizacao;
    }

    public DominioModel getTipo_utilizador() {
        return tipo_utilizador;
    }

    public void setTipo_utilizador(DominioModel tipo_utilizador) {
        this.tipo_utilizador = tipo_utilizador;
    }

    public EntidadeModel getEntidade() {
        return entidade;
    }

    public void setEntidade(EntidadeModel entidade) {
        this.entidade = entidade;
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
