package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho_TipoQueixa {
    
    int getAno();

    int getConcelho();

    String getConcelhoDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
