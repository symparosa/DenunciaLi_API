package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Tipo_CrimeInputUpdateDto {
    
    @Schema(description = "O identificador (ID) do tipo de crime", required = true)
    private int id;

    @Schema(description = "O nome do tipo de crime")
    private String nome;

    @Schema(description = "Imagem que representa o tipo de crime")
    private String logotipo;

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

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }
}
