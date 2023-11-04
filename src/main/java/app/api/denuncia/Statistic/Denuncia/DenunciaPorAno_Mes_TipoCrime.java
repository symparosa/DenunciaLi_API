package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Mes_TipoCrime {
    
    int getAno();

    int getMes();

    String getMesDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
