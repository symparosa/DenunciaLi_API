package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_TipoCrime_Genero {
    
    int getAno();

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
