package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_TipoCrime {
    
    int getAno();

    int getIdIlha();

    String getIlhaDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
