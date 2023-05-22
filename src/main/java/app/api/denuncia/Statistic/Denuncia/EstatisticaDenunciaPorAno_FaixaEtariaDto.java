package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_FaixaEtariaDto {
    
    int getAno();

    String getFaixa_etaria();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
