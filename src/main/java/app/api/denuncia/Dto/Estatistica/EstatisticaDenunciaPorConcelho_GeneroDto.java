package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelho_GeneroDto {
    
    int getConcelho();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
