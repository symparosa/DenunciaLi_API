package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_Concelho_TipoCrimeDto {
    
    int getAno();

    int getConcelho();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
