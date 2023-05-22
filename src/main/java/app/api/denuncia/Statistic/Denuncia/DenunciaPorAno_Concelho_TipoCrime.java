package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho_TipoCrime {
    
    int getAno();

    int getConcelho();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
