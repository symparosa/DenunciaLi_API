package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_Ilha_TipoCrimeDto {
    
    int getAno();

    int getIdIlha();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
