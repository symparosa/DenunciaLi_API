package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_Genero {
    
    int getAno();

    int getIdIlha();

    String getIlhaDesc();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
