package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorConcelho_TipoCrime_TipoQueixa {
    
    int getConcelho();

    String getConcelhoDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
