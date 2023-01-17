package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_TipoCrimeDto {
    
    int getAno();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
