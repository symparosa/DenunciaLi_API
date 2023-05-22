package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_TipoCrimeDto {
    
    int getAno();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
