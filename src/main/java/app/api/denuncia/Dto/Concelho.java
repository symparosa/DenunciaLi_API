package app.api.denuncia.Dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Concelho {
    private String id_concelho;
    private String nome_concelho;
    private String nome_norm_concelho;
}