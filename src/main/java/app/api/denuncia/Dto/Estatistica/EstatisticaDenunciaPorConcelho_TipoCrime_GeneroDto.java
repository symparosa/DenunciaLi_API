package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto {
    
    int getConcelho();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
