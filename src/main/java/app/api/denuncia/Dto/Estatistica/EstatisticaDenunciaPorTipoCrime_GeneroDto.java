package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorTipoCrime_GeneroDto {
    
    int getTipoCrime();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
