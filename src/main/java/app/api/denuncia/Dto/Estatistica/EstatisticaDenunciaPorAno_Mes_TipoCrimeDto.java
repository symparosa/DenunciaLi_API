package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_Mes_TipoCrimeDto {
    
    int getAno();

    int getMes();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
