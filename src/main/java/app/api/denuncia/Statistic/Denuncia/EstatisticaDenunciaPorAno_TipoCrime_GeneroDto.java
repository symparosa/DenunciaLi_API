package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorAno_TipoCrime_GeneroDto {
    
    int getAno();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
