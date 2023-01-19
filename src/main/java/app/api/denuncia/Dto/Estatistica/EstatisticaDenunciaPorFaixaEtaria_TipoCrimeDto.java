package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorFaixaEtaria_TipoCrimeDto {

    String getFaixa_etaria();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
