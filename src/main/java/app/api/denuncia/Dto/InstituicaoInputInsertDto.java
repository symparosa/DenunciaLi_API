package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class InstituicaoInputInsertDto {

    @Schema(description = "O email da intituição de apoio", required = true)
    private String email;

    @Schema(description = "O identificador (ID) da localização", required = true)
    private int endereco;

    @Schema(description = "Imagem que representa a intituição de apoio", required = true)
    private String logotipo;

    @Schema(description = "O nome da intituição de apoio", required = true)
    private String nome;

    @Schema(description = "O telefone da intituição de apoio", required = true)
    private String telefone;

    @Schema(description = "O identificador (ID) do tipo de crime", required = true)
    private int tipoCrime;

    @Schema(description = "A porta onde fica a intituição de apoio", required = true)
    private String porta;

    @Schema(description = "A rua onde fica a intituição de apoio", required = true)
    private String rua;

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

    public int getTipoCrime() {
        return tipoCrime;
    }

    public void setTipoCrime(int tipoCrime) {
        this.tipoCrime = tipoCrime;
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
