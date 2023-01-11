package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class UtilizadorInputUpdateDto {

    @Schema(description = "O identificador (ID) do utilizador", required = true)
    private int id;

    @Schema(description = "O nome do utilizador")
    private String nome;

    @Schema(description = "O apelido do utilizador")
    private String apelido;

    @Schema(description = "O email do utilizador")
    private String email;

    @Schema(description = "Foto de perfil do utilizador")
    private String foto;

    @Schema(description = "O telemovel do utilizador")
    private String telemovel;

    @Schema(description = "A localização do utilizador")
    private int localizacao;

    public UtilizadorInputUpdateDto() {
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getTelemovel() {
        return telemovel;
    }

    public void setTelemovel(String telemovel) {
        this.telemovel = telemovel;
    }

    public int getLocalizacao() {
        return localizacao;
    }

    public void setLocalizacao(int localizacao) {
        this.localizacao = localizacao;
    }
}
