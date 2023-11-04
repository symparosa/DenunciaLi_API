package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorTipoCrime_TipoQueixa {

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
