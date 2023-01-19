package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorTipoCrime_TipoQueixaDto {

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
