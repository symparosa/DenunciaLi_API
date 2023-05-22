package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto {
    
    int getAno();

    int getIdIlha();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
