package app.api.denuncia.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Message {

    private final String Message03 = "dados já existentes.";
    private final String Message04 = "falha no sistema.";
    private final String Message05 = "lista vazia.";
    private final String Message07 = "status inválido.";
    private final String Message08 = "dados duplicados.";
    private final String Message10 = "email enviado com sucesso.";
    private final String Message11 = "falha ao enviar email.";
    private final String Message12 = "dados inválido.";
    private final String Message16 = "conflito entre user logado.";
    private final String Message17 = "user não logado.";
    private final String Message18 = "senha válida.";
    private final String Message19 = "não é possível reprocessar";

    public String getMessage01(String metodo) {
        return metodo + " dados com sucesso.";
    }

    public String getMessage02(String metodo) {
        return "falha ao " + metodo + " dados.";
    }

    public String getMessage06(String obj) {
        return obj + " não existe.";
    }

    public String getMessage09(String campo) {
        return campo + " não pode ser null ou vazio.";
    }

    public String getMessage13(String metodo) {
        return metodo + " com sucesso.";
    }

    public String getMessage14(String metodo) {
        return "falha no " + metodo;
    }

    public String getMessage15(String metodo) {
        return metodo + " já existe.";
    }
}
