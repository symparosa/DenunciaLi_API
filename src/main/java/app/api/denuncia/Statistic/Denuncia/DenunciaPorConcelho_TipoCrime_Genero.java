package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorConcelho_TipoCrime_Genero {
    
    int getConcelho();

    String getConcelhoDesc();

    int getTipoCrime();

    String getTipoCrimeDesc();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
