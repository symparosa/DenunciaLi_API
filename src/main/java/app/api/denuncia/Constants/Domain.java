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
}
