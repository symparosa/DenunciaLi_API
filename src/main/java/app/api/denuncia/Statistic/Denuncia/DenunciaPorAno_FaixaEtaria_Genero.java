package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_FaixaEtaria_Genero {
    
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
