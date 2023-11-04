package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_TipoCrime_Genero {
    
    int getAno();

    int getIdIlha();

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
