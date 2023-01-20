package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorIlha_GeneroDto {
    
    int getIlha();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
