package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorConcelho_Genero {
    
    int getConcelho();

    String getConcelhoDesc();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
