package app.api.denuncia.Models;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_localizacao")
public class LocalizacaoModel implements Serializable {

    @Id
    private String id;

    private String nome;

    private String pais;

    private String ilha;

    private String concelho;

    private String freguesia;

    private String zona;

    private String nome_norm;

    private String nivel_detalhe;

    private String colmatch;

    @Lob
    private String polygonarea;
}
