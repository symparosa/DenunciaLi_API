package app.api.denuncia.Dto;

import io.swagger.v3.oas.annotations.media.Schema;

public class Tipo_CrimeInputInsertDto {

    @Schema(description = "O nome do tipo de crime", required = true)
    private String nome;

    @Schema(description = "Imagem que representa o tipo de crime", required = true)
    private String logotipo;

    public Tipo_CrimeInputInsertDto() {
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
