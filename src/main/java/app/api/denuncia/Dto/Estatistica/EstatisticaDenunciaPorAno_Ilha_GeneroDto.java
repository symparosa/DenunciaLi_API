package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_Ilha_GeneroDto {
    
    int getAno();

    int getIdIlha();

    int getQuantidadeFeminino();

    int getQuantidadeMasculino();

    int getQuantidadeAnonimo();

    int getTotal();

    Float getPercentagemFeminino();

    Float getPercentagemMasculino();

    Float getPercentagemAnonimo();
}
