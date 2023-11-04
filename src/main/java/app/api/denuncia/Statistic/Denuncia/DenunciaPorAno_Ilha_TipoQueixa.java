package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_TipoQueixa {
    
    int getAno();

    int getIdIlha();

    String getIlhaDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
