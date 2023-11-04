package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorTipoQueixa {

    int getTipoQueixa();

    String getTipoQueixaDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
