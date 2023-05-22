package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_TipoCrime_TipoQueixa {
    
    int getAno();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
