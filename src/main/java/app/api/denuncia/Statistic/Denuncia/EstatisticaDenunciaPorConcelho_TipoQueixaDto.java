package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorConcelho_TipoQueixaDto {
    
    int getConcelho();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
