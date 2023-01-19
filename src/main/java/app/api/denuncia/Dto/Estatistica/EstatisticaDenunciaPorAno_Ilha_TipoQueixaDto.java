package app.api.denuncia.Dto.Estatistica;

public interface EstatisticaDenunciaPorAno_Ilha_TipoQueixaDto {
    
    int getAno();

    int getIdIlha();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
