package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_Concelho_TipoCrime_GeneroDto {
    
    int getAno();

    int getConcelho();

    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();
}
