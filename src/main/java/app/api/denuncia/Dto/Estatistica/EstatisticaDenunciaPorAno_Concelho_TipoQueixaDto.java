package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_Concelho_TipoQueixaDto {
    
    int getAno();

    int getConcelho();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
