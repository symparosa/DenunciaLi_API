package app.api.denuncia.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReprocessamentoDenuncia {
    private Integer idRepro;
    private LocalDateTime dataCriacaoRepro;
    private Integer estadoRepro;
    private String reprocessamento;
    private Integer estadoDenuncia;
    private LocalDateTime dataDenuncia;
    private String tipoCrime;
    private String tipoQueixa;
}
