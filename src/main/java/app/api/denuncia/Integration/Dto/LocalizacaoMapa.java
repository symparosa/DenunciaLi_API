package app.api.denuncia.Integration.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LocalizacaoMapa {
    private String lat;
    private String lng;
}
