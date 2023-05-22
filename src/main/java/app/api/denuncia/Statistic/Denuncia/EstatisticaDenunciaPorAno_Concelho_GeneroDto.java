package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_Concelho_GeneroDto {
    
    int getAno();

    int getConcelho();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
