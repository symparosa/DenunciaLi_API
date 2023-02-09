package app.api.denuncia.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Domain {

    private final String TipoCrime = "TIPO_CRIME";
    private final String TipoUser = "TIPO_UTILIZADOR";
    private final String TipoEntidade = "TIPO_ENTIDADE";
    private final String TipoContato = "TIPO_CONTATO";
    private final String TipoInfoLegal = "TIPO_INFORMACAO_LEGAL";
    private final String TipoArquivo = "TIPO_ARQUIVO";
    private final String TipoQueixa = "TIPO_QUEIXA";
    private final String GrauParentesco = "GRAU_PARENTESCO";
}
