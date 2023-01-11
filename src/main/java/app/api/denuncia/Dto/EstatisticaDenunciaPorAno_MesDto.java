package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_MesDto{
    
    String getAno();

    int getMes();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
