package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_Mes_TipoCrimeDto {
    
    String getAno();

    int getMes();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
