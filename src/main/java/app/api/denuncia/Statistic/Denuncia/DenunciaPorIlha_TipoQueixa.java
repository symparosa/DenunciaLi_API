package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoQueixa {
    
    int getIlha();

    String getIlhaDesc();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
