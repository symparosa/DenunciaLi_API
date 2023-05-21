package app.api.denuncia.Integration.Dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Denunciante {
    private String nome;
    private String zona_lugar;
    private String referencia_morada;
    private LocalizacaoMapa local_mapa;
    private String apelido;
    private String codigo_postal;
    private LocalDateTime data_nascimento;
    private String doc_identificacao;
    private String foto;
    private String genero;
}
