package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorIlha_TipoCrimeDto {
    
    int getIlha();

    int getTipoCrime();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
