package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_FaixaEtariaDto {
    
    int getAno();

    String getFaixa_etaria();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
