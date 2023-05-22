package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorIlha_TipoQueixaDto {
    
    int getIlha();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
