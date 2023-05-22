package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_Concelho_TipoCrime_TipoQueixaDto {
    
    int getAno();

    int getConcelho();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
