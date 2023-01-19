package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorIlha_TipoCrime_TipoQueixaDto {
    
    int getIlha();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
