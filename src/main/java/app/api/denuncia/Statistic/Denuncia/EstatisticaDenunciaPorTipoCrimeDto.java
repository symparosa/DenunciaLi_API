package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorTipoCrimeDto {

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
