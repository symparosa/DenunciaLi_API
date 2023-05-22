package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoCrime_Genero {
    
    int getIlha();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
