package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorConcelho_TipoCrime_TipoQueixaDto {
    
    int getConcelho();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
