package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_TipoCrime {
    
    int getAno();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
