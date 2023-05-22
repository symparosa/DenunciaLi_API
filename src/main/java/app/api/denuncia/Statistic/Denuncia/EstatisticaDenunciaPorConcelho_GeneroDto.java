package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorConcelho_GeneroDto {
    
    int getConcelho();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
