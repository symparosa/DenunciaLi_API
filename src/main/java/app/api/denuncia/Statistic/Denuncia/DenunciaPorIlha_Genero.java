package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_Genero {
    
    int getIlha();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
