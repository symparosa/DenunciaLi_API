package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto {
    
    int getIlha();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
