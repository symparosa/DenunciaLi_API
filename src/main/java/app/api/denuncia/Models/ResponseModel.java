package app.api.denuncia.Models;

import java.util.List;

import app.api.denuncia.Constants.ResponseType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseModel {

    @Schema(description = "Mensagem de saída")
    private List<String> message;

    @Schema(description = "Tipo de saída")
    private ResponseType responseType;

    @Schema(description = "Código de saída")
    private int responseCode;

    @Schema(description = "Objeto de saída")
    private Object object;
}
