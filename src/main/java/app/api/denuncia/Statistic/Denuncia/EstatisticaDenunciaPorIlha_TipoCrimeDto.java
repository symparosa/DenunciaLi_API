package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorIlha_TipoCrimeDto {
    
    int getIlha();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
