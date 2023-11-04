package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho_TipoCrime {
    
    int getAno();

    int getConcelho();

    String getConcelhoDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
