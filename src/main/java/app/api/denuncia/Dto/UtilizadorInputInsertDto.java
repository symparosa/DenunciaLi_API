package app.api.denuncia.Dto;

import java.util.Date;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilizadorInputInsertDto {

    @Schema(description = "O username do utilizador", required = true)
    private String username;

    @Schema(description = "O nome do utilizador", required = true)
    private String nome;

    @Schema(description = "O apelido do utilizador", required = true)
    private String apelido;

    @Schema(description = "A data de nascimento do utilizador", required = true)
    private Date dataNascimento;

    @Schema(description = "O bi ou cni do utilizador", required = true)
    private String numeroDeIdentificacao;

    @Schema(description = "O email do utilizador", required = true)
    private String email;

    @Schema(description = "Foto de perfil do utilizador", required = true)
    private String foto;

    @Schema(description = "O genero do utilizador", required = true)
    private String genero;

    @Schema(description = "O telemovel do utilizador", required = true)
    private String telemovel;

    @Schema(description = "A localização do utilizador", required = false)
    private Integer localizacao;

    @Schema(description = "A localização do utilizador no mapa", required = false)
    private String moradaGps_Map;

    @Schema(description = "O tipo de utilizador", required = true)
    private int tipoUtilizador;

    public UtilizadorInputInsertDto() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public Integer getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(Integer localizacao) {
        this.localizacao = localizacao;
    }

    public String getNumeroDeIdentificacao() {
        return numeroDeIdentificacao;
    }

    public void setNumeroDeIdentificacao(String numeroDeIdentificacao) {
        this.numeroDeIdentificacao = numeroDeIdentificacao;
    }

    public int getTipoUtilizador() {
        return tipoUtilizador;
    }

    public void setTipoUtilizador(int tipoUtilizador) {
        this.tipoUtilizador = tipoUtilizador;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getMoradaGps_Map() {
        return moradaGps_Map;
    }

    public void setMoradaGps_Map(String moradaGps_Map) {
        this.moradaGps_Map = moradaGps_Map;
    }
}
