package app.api.denuncia.Integration.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Anexos {
    private String arquivo;
    private String tipo_arquivo;
}
