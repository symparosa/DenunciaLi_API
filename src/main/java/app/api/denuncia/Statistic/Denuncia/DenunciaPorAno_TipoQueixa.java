package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_TipoQueixa {
    
    int getAno();

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
