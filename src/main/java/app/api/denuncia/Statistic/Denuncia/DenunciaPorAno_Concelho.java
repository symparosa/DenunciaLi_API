package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho {
    
    int getAno();

    int getConcelho();

    String getConcelhoDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
