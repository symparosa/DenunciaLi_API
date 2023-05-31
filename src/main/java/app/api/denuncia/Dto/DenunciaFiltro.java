package app.api.denuncia.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DenunciaFiltro {
    private String Denunciante;
    private String Idade;
    private String DenuncianteGenero;
    private String TipoCrime;
    private String TipoQueixa;
    private String DescricaoDenuncia;
    private Integer EstadoDenuncia;
    private String LocalOcorrencia;
    private LocalDateTime DataDenuncia;
    private Integer QuantArquivoAnexado;
}
