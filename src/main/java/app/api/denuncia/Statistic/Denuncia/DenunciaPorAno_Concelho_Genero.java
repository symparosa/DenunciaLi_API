package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Concelho_Genero {
    
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
