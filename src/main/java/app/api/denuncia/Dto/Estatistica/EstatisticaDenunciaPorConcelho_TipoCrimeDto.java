package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelho_TipoCrimeDto {
    
    int getConcelho();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
