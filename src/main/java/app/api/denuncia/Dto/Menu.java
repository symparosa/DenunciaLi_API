package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Menu {
    private Integer id_menu;
    private String codigo;
    private Integer estado_menu;
    private Integer id_menu_pai;
    private String menu_icon;
    private Integer visibilidade;
    private String titulo;
    private Integer estado_menu_perfil;
    private Integer perfil;
}
