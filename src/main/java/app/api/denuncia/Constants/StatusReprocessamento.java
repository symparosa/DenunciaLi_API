package app.api.denuncia.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class StatusReprocessamento {
    private final int NaoReprocessado = 1;
    private final int ReprocessadoComSucesso = 2;
    private final int ErroNoReprocessamento = 3;
}
