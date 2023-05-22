package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Mes_TipoCrime {
    
    int getAno();

    int getMes();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
