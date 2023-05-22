package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_ConcelhoDto {
    
    int getAno();

    int getConcelho();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
