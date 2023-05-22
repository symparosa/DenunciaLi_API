package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho_TipoCrime_TipoQueixa {
    
    int getAno();

    int getConcelho();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
