package app.api.denuncia.Statistic.Denuncia;

public interface DenunciaPorIlha_TipoCrime_TipoQueixa {
    
    int getIlha();

    int getTipoCrime();

    int getTipoQueixa();

    int getQuantidade();

    int getTotal();

    Float getPercentagem();
}
