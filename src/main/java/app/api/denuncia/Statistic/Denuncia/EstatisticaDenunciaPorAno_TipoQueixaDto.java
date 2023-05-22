package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_TipoQueixaDto {
    
    int getAno();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
