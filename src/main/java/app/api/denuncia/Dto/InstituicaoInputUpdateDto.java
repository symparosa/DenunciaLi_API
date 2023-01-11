package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class InstituicaoInputUpdateDto {
 
    @Schema(description = "O identificador (ID) da intituição de apoio",required = true) 
    private int id;

    @Schema(description = "O email da intituição de apoio")
    private String email;

    @Schema(description = "O identificador (ID) da localização")
    private int endereco;

    @Schema(description = "Imagem que representa a intituição de apoio")
    private String logotipo;

    @Schema(description = "O nome da intituição de apoio")
    private String nome;

    @Schema(description = "O telefone da intituição de apoio")
    private String telefone;

    @Schema(description = "A porta onde fica a intituição de apoio")
    private String porta;

    @Schema(description = "A rua onde fica a intituição de apoio")
    private String rua;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEndereco() {
        return endereco;
    }

    public void setEndereco(int endereco) {
        this.endereco = endereco;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
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
}
