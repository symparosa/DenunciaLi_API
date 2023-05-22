package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto {
    
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
