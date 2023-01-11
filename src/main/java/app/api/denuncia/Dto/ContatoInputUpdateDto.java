package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class ContatoInputUpdateDto {

    @Schema(description = "O identificador (ID) do contato", required = true)
    private int id;

    @Schema(description = "O nome do contato")
    private String nome;

    @Schema(description = "O número de telefone do contato")
    private String telefone;

    @Schema(description = "Imagem que representa o contato")
    private String logotipo;

    public ContatoInputUpdateDto() {
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

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }
}
