package app.api.denuncia.Models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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

    private String polygonarea;
}
