package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Mes{
    
    int getAno();

    int getMes();

    String getMesDesc();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
