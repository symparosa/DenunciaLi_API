package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Botao {
    private Integer id_botao;
    private String codigo;
    private Integer estado_botao;
    private String botao_icon;
    private String descricao;
    private Integer estado_transacao;
    private Integer perfil;
}
