package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_FaixaEtariaDto {
    
    String getAno();

    String getFaixa_etaria();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
