package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto {
    
    int getAno();

    String getFaixa_etaria();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
