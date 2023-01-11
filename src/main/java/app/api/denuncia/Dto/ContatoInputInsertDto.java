package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ContatoInputInsertDto {

    @Schema(description = "O nome do contato", required = true)
    private String nome;

    @Schema(description = "O número de telefone do contato")
    private String telefone;

    @Schema(description = "Imagem que representa o contato", required = true)
    private String logotipo;

    public ContatoInputInsertDto() {
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }
}
