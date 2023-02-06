package app.api.denuncia.Constants;

import java.util.ArrayList;
import java.util.List;

import app.api.denuncia.Models.ResponseModel;
public class GlobalFunctions {

    private Message message = new Message();
    private Status status = new Status();
    private final int id_user_logado = 9362;
    private List<String> msg = new ArrayList<>();

    public ResponseModel getResponse(int code, ResponseType type, List<String> msg, Object obj) {

        ResponseModel response = new ResponseModel();

        response.setResponseCode(code);
        response.setResponseType(type);
        response.setMessage(msg);
        response.setObject(obj);

        return response;
    }

    public ResponseModel getResponseError(List<String> listMsg) {
        return getResponse(0, ResponseType.Erro, listMsg, null);
    }

    public ResponseModel validateGetUpdateMsg(String metodo, Integer result) {

        if (result != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, null);

        } else {

            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public ResponseModel validateGetSaveMsgWithList(String metodo, List<?> lista) {

        if (lista != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, lista);

        } else {

            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public ResponseModel validateGetSaveMsgWithObj(String metodo, Object obj) {

        if (obj != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, obj);

        } else {
            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public ResponseModel validateGetListMsg(String metodo, List<?> lista) {

        if (lista != null && !lista.isEmpty()) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, lista);

        } else if (lista == null) {

            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);

        } else {

            msg.add(message.getMessage05());
            return getResponseError(msg);
        }
    }

    public List<Integer> getStatusAtivoInativo() {

        List<Integer> estados = new ArrayList<>();
        estados.add(status.getInativo());
        estados.add(status.getAtivo());

        return estados;
    }

    public Boolean validateStatus(int estado) {

        if (estado == status.getAtivo() || estado == status.getInativo() || estado == status.getEliminado()) {
            return true;
        }
        return false;
    }

    public int getId_user_logado() {
        return id_user_logado;
    }

    public void clearList(List<String> msg) {

        if (!msg.isEmpty()) {
            msg.clear();
        }
    }
}
