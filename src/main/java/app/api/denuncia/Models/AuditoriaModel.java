package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;

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
