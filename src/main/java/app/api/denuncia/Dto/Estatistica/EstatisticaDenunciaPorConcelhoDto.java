package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelhoDto {
    
    int getConcelho();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
