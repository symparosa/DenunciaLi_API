package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_Ilha_TipoCrime_GeneroDto {
    
    int getAno();

    int getIdIlha();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
