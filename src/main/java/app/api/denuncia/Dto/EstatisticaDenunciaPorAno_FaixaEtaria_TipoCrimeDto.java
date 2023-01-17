package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_FaixaEtaria_TipoCrimeDto {
    
    int getAno();

    String getFaixa_etaria();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
