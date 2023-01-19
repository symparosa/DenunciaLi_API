package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_ConcelhoDto {
    
    int getAno();

    int getConcelho();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
