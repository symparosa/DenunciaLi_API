package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_TipoCrimeDto {
    
    int getAno();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
