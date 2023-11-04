package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_FaixaEtaria_TipoCrime {
    
    int getAno();

    String getFaixa_etaria();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
