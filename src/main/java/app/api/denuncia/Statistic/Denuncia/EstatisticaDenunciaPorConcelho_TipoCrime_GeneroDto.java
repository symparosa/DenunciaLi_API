package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorConcelho_TipoCrime_GeneroDto {
    
    int getConcelho();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
