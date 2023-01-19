package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelho_TipoQueixaDto {
    
    int getConcelho();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
