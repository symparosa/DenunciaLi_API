package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_GeneroDto {
    
    int getAno();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
