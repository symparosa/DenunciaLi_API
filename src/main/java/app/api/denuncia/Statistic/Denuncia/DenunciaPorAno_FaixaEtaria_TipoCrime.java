package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_FaixaEtaria_TipoCrime {
    
    int getAno();

    String getFaixa_etaria();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
