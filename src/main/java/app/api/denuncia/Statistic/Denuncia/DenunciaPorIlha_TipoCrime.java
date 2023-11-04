package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoCrime {
    
    int getIlha();

    String getIlhaDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
