package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorFaixaEtaria_GeneroDto {

    String getFaixa_etaria();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
