package app.api.denuncia.Dto.Response;

import app.api.denuncia.Constants.ResponseType;
import io.swagger.v3.oas.annotations.media.Schema;

public class ResponseDto {

    @Schema(description = "Mensagem de saída")
    private String message;

    @Schema(description = "Tipo de saída")
    private ResponseType responseType;

    @Schema(description = "Código de saída")
    private int responseCode;

    @Schema(description = "Objeto de saída")
    private Object object;

    public int getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(int responseCode) {
        this.responseCode = responseCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseType getResponseType() {
        return responseType;
    }

    public void setResponseType(ResponseType responseType) {
        this.responseType = responseType;
    }

    public Object getObject() {
        return object;
    }

    public void setObject(Object object) {
        this.object = object;
    }
}
