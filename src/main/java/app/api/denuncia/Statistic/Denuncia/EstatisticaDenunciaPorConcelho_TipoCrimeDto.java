package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorConcelho_TipoCrimeDto {
    
    int getConcelho();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
