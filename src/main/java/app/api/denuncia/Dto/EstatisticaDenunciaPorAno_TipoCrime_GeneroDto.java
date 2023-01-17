package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_TipoCrime_GeneroDto {
    
    int getAno();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
