package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_TipoQueixaDto {
    
    int getAno();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
