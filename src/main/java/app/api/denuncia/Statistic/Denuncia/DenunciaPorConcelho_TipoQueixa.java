package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorConcelho_TipoQueixa {
    
    int getConcelho();

    String getConcelhoDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
