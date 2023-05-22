package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto {
    
    int getConcelho();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
