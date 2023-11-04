package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorFaixaEtaria_TipoCrime {

    String getFaixa_etaria();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
