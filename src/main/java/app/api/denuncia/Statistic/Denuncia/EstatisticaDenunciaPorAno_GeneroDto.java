package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_GeneroDto {
    
    int getAno();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
