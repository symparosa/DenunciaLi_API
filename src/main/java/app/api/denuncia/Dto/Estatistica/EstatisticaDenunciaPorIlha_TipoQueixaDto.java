package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorIlha_TipoQueixaDto {
    
    int getIlha();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
