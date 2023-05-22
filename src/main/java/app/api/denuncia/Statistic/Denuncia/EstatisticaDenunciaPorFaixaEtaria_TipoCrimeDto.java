package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto {

    String getFaixa_etaria();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
