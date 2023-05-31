package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FaixaEtaria {
    private Integer idade_inicio;
    private Integer idade_fim;
    private String faixa_etaria;
}
