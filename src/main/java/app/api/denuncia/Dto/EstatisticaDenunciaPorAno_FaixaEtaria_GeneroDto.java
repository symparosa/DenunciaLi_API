package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_FaixaEtaria_GeneroDto {
    
    String getAno();

    String getFaixa_etaria();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
