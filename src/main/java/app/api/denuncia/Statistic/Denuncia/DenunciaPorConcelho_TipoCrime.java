package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorConcelho_TipoCrime {
    
    int getConcelho();

    String getConcelhoDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
