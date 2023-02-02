package app.api.denuncia.Constants;

import java.util.ArrayList;
import java.util.List;

import app.api.denuncia.Dto.Response.ResponseDto;

public class GlobalFunctions {

    private Message msg = new Message();
    private Status status = new Status();
    private final int id_user_logado = 9362;

    public ResponseDto getResponse(int code, ResponseType type, String msg, Object obj) {

        ResponseDto response = new ResponseDto();

        response.setResponseCode(code);
        response.setResponseType(type);
        response.setMessage(msg);
        response.setObject(obj);

        return response;
    }

    public ResponseDto validateGetUpdateMsg(String metodo, Integer result) {
        if (result != null) {
            return getResponse(1, ResponseType.Sucesso, msg.getMessage01(metodo), null);
        } else {
            return getResponse(0, ResponseType.Erro, msg.getMessage02(metodo), null);
        }
    }

    public ResponseDto validateGetSaveMsgWithList(String metodo, List<?> lista) {
        if (lista != null) {
            return getResponse(1, ResponseType.Sucesso, msg.getMessage01(metodo), lista);
        } else {
            return getResponse(0, ResponseType.Erro, msg.getMessage02(metodo), null);
        }
    }

    public ResponseDto validateGetSaveMsgWithObj(String metodo, Object obj) {
        if (obj != null) {
            return getResponse(1, ResponseType.Sucesso, msg.getMessage01(metodo), obj);
        } else {
            return getResponse(0, ResponseType.Erro, msg.getMessage02(metodo), null);
        }
    }

    public ResponseDto validateGetListMsg(String metodo, List<?> lista) {
        if (lista != null && !lista.isEmpty()) {
            return getResponse(1, ResponseType.Sucesso, msg.getMessage01(metodo), lista);
        } else if (lista == null) {
            return getResponse(0, ResponseType.Erro, msg.getMessage02(metodo), null);
        } else {
            return getResponse(0, ResponseType.Erro, msg.getMessage05(), null);
        }
    }

    public List<Integer> getStatusAtivoInativo() {
        
        List<Integer> estados = new ArrayList<>();
        estados.add(status.getInativo());
        estados.add(status.getAtivo());

        return estados;
    }

    public List<Integer> getTodosStatus() {
        
        List<Integer> estados = new ArrayList<>();
        estados.add(status.getInativo());
        estados.add(status.getAtivo());
        estados.add(status.getEliminado());

        return estados;
    }

    public int getId_user_logado() {
        return id_user_logado;
    }
}
