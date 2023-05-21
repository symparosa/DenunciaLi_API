package app.api.denuncia.Constants;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Status {
    private final int Ativo = 1;
    private final int Inativo = 0;
    private final int Eliminado = -1;
}
