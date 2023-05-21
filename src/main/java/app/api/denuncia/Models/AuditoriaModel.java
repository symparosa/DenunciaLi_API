package app.api.denuncia.Models;

import java.io.Serializable;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_auditoria")
public class AuditoriaModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private Integer id_objeto;

    @Column(nullable = false)
    private String tipo_objeto;

    @Lob
    private String valor_atual;

    @Lob
    private String valor_novo;

    @Column(nullable = false)
    private Integer id_utilizador;

    @Column(nullable = false)
    private String accao;

    private Integer estado;

    private LocalDateTime data_criacao;
}
