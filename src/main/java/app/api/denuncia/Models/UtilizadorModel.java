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

@Entity
@Table(name = "utilizador")
public class UtilizadorModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column()
    @NotNull()
    
    private String nome;

    @Column(unique = true)
    @NotNull()
    private String username;

    @Column()
    @NotNull()
    private String apelido;

    @Column()
    @NotNull()
    private String genero;

    @Column(unique = true)
    @NotNull()
    private String email;

    @Column(unique = true)
    private String cni;

    @Column(unique = true)
    private String bi;

    @Column(unique = true)
    private String telemovel;

    @Lob
    @NotNull()
    @Column
    private String foto;

    @Column(columnDefinition = "default '1869-04-04'")
    private Date dataNascimento;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel morada;

    @ManyToOne
    @JoinColumn(name = "tipoUtilizador_fk")
    private Tipo_UtilizadorModel tipoUtilizador;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public UtilizadorModel() {
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

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCni() {
        return cni;
    }

    public void setCni(String cni) {
        this.cni = cni;
    }

    public String getBi() {
        return bi;
    }

    public void setBi(String bi) {
        this.bi = bi;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public LocalizacaoModel getMorada() {
        return morada;
    }

    public void setMorada(LocalizacaoModel morada) {
        this.morada = morada;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Tipo_UtilizadorModel getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(Tipo_UtilizadorModel tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }
}
