package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_MesDto{
    
    int getAno();

    int getMes();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
