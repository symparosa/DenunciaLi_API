package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorTipoCrime {

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
