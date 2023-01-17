package app.api.denuncia.Dto;

public interface EstatisticaDenunciaPorAno_Ilha_TipoCrime_TipoQueixaDto {
    
    int getAno();

    int getIdIlha();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
