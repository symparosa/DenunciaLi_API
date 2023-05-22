package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto {
    
    int getAno();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
