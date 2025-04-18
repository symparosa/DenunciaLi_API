package app.api.denuncia.Dto;

import java.util.List;

import app.api.denuncia.Enums.ResponseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Response {

    @Schema(description = "Mensagem de saída")
    private List<String> message;

    @Schema(description = "Tipo de saída")
    private ResponseType responseType;

    @Schema(description = "Código de saída", example = "1")
    private int responseCode;

    @Schema(description = "Objeto de saída")
    private Object object;
}
