package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Localizacao {
    private String id_localidade;
    private String nome_localidade;
    private String nome_norm_localidade;
}
