package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoCrime_TipoQueixa {
    
    int getIlha();

    String getIlhaDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
