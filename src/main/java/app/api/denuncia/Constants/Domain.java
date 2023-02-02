package app.api.denuncia.Constants;

public class Domain {

    private final String DomainTipoCrime = "TIPO_CRIME";
    private final String DomainTipoUtilizador = "TIPO_UTILIZADOR";
    private final String DomainTipoEntidade = "TIPO_ENTIDADE";
    private final String DomainTipoContato = "TIPO_CONTATO";

    public Domain() {
    }

    public String getDomainTipoCrime() {
        return DomainTipoCrime;
    }

    public String getDomainTipoUtilizador() {
        return DomainTipoUtilizador;
    }

    public String getDomainTipoEntidade() {
        return DomainTipoEntidade;
    }

    public String getDomainTipoContato() {
        return DomainTipoContato;
    }
}
