package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorAno_Ilha_TipoCrime_TipoQueixa {
    
    int getAno();

    int getIdIlha();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
