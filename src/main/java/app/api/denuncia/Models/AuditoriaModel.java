package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_auditoria")
public class AuditoriaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int id_objeto;

    @Column(nullable = false)
    private String tipo_objeto;

    @Lob
    private String valor_atual;

    @Lob
    private String valor_novo;

    @Column(nullable = false)
    private int id_utilizador;

    @Column(nullable = false)
    private String accao;

    private int estado;

    private Date data_criacao;
}
