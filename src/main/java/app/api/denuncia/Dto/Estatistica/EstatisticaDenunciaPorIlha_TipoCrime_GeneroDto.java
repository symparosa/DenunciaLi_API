package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorIlha_TipoCrime_GeneroDto {
    
    int getIlha();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
