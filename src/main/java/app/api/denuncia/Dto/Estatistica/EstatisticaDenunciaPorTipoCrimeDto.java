package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorTipoCrimeDto {

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
