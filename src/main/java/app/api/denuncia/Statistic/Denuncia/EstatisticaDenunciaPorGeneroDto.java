package app.api.denuncia.Statistic.Denuncia;

public interface EstatisticaDenunciaPorGeneroDto {

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
