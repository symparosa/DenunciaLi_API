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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "instituicao_apoio")
public class Instituicao_ApoioModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
    @NotNull()
    private String nome;

    @Column(unique = true)
    @NotNull()
    private String email;

    @Column(unique = true)
    @NotNull()
    private String telefone;

    @ManyToOne
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel endereco;

    @Column
    private String porta;

    @Column
    private String rua;

    @Lob
    @NotNull()
    @Column
    private String logotipo;

    @OneToOne
    @JoinColumn(name = "tipoCrime_fk")
    private Tipo_CrimeModel crime;

    @Column
    private int estado;

    @Column
    private Date dataCriacao;

    @Column
    private Date dataAtualizacao;

    public Instituicao_ApoioModel() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public LocalizacaoModel getEndereco() {
        return endereco;
    }

    public void setEndereco(LocalizacaoModel endereco) {
        this.endereco = endereco;
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

    public Tipo_CrimeModel getCrime() {
        return crime;
    }

    public void setCrime(Tipo_CrimeModel crime) {
        this.crime = crime;
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

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }
}
