package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_TipoCrime_TipoQueixa {
    
    int getAno();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
