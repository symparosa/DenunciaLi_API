package app.api.denuncia.Constants;

public class Message {
    
    private final String Message03 = "dados já existentes.";
    private final String Message04 = "falha no sistema.";
    private final String Message05 = "lista vazia.";
    private final String Message06 = "não existe.";
    private final String Message07 = "status inválido.";
    private final String Message08 = "dados duplicados.";
    
    public Message() {
    }

    public String getMessage01(String metodo) {
        return metodo+" dados com sucesso.";
    }

    public String getMessage02(String metodo) {
        return "falha ao "+metodo+" dados.";
    }

    public String getMessage03() {
        return Message03;
    }

    public String getMessage04() {
        return Message04;
    }

    public String getMessage05() {
        return Message05;
    }

    public String getMessage06() {
        return Message06;
    }

    public String getMessage07() {
        return Message07;
    }

    public String getMessage08() {
        return Message08;
    }    
}

