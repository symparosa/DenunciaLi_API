package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoCrime_Genero {
    
    int getIlha();

    String getIlhaDesc();

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
