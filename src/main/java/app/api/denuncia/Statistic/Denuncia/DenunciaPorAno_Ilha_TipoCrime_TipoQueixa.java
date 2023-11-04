package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_TipoCrime_TipoQueixa {
    
    int getAno();

    int getIdIlha();

    String getIlhaDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
