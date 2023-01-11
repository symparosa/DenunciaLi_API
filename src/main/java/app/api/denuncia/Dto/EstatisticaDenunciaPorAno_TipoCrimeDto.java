package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_TipoCrimeDto {
    
    String getAno();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
