package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_TipoQueixaDto {
    
    int getAno();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
