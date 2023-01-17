package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_Ilha_GeneroDto {
    
    int getAno();

    int getIdIlha();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
