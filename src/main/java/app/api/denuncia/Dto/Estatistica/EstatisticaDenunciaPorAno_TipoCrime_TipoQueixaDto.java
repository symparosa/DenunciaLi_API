package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_TipoCrime_TipoQueixaDto {
    
    int getAno();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
