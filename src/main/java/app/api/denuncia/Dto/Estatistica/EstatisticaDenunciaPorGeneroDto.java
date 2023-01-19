package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorGeneroDto {

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
