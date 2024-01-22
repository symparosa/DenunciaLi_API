package app.api.denuncia.Utilities;

import java.io.BufferedReader;
import java.io.FileReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.Status;
import app.api.denuncia.Constants.StatusReprocessamento;
import app.api.denuncia.Dto.EmailDetails;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;

public class GlobalFunctions {

    private Message message = new Message();
    private Status status = new Status();
    private List<String> msg = new ArrayList<>();
    private StatusReprocessamento statusReprocessamento = new StatusReprocessamento();

    public Response getResponse(int code, ResponseType type, List<String> msg, Object obj) {

        Response response = new Response();

        response.setResponseCode(code);
        response.setResponseType(type);
        response.setMessage(msg);
        response.setObject(obj);

        return response;
    }

    public Response getResponseError(List<String> listMsg) {
        return getResponse(0, ResponseType.Erro, listMsg, null);
    }

    public Response validateGetUpdateMsg(String metodo, Integer result) {

        clearList(msg);

        if (result != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, null);

        } else {

            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public Response validateGetSaveMsgWithList(String metodo, List<?> lista) {

        clearList(msg);

        if (lista != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, null);

        } else {

            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public Response validateGetSaveMsgWithObj(String metodo, Object obj) {

        clearList(msg);

        if (obj != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, obj);

        } else {
            msg.add(message.getMessage02(metodo));
            return getResponseError(msg);
        }
    }

    public Response validateGetMsgWithObj(String metodo, Object obj, String entities) {

        clearList(msg);

        if (obj != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, obj);

        } else {
            msg.add(message.getMessage06(entities));
            return getResponseError(msg);
        }
    }

    public Response validateGetListMsg(String metodo, List<?> lista) {

        clearList(msg);

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

    public Response validateMsgEncript(String metodo, String encript) {

        clearList(msg);

        if (encript != null) {

            msg.add(message.getMessage01(metodo));
            return getResponse(1, ResponseType.Sucesso, msg, encript);

        } else {
            msg.add(message.getMessage02(metodo));
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

    public Boolean validateStatusReprocessamento(int estado) {

        if (estado == statusReprocessamento.getReprocessadoComSucesso()
                || estado == statusReprocessamento.getErroNoReprocessamento()
                || estado == statusReprocessamento.getNaoReprocessado()) {
            return true;
        }
        return false;
    }

    public void clearList(List<String> msg) {

        if (!msg.isEmpty()) {
            msg.clear();
        }
    }

    public String generateHash() {

        MessageDigest digest;
        byte[] encodedhash;

        try {

            digest = MessageDigest.getInstance("SHA-256");
            encodedhash = digest.digest(generateSalt());
            return bytesToHex(encodedhash);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder(2 * hash.length);
        for (int i = 0; i < hash.length; i++) {
            String hex = Integer.toHexString(0xff & hash[i]);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public byte[] generateSalt() {
        SecureRandom random = new SecureRandom();
        byte bytes[] = new byte[20];
        random.nextBytes(bytes);
        return bytes;
    }

    public EmailDetails createEmail(String to, String Body, String Subject) {

        EmailDetails emailDetail = new EmailDetails();

        emailDetail.setRecipient(to);
        emailDetail.setMsgBody(Body);
        emailDetail.setSubject(Subject);

        return emailDetail;
    }

    public String getTemplate(String path) {

        StringBuilder html;
        FileReader fr;
        BufferedReader br;
        String val, result;

        try {

            html = new StringBuilder();

            fr = new FileReader(path);

            br = new BufferedReader(fr);

            while ((val = br.readLine()) != null) {
                html.append(val);
            }

            result = html.toString();
            br.close();

            return result;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
