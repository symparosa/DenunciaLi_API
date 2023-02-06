package app.api.denuncia.Models;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_denunciante")
public class DenuncianteModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Schema(description = "O hash do denunciante", hidden = true)
    private String hash;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String apelido;

    @Column(nullable = false)
    private String genero;

    @Column(unique = true, nullable = false)
    private String doc_identificacao;

    @Lob
    private String foto_perfil;

    private Date data_nascimento;

    private String referencia_morada;

    private String codigo_postal;

    @ManyToOne()
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "Id do último utilizador a alterar os dados")
    private Integer last_user_change;

    private String localizacao_mapa;

    private Integer estado;

    private Date data_criacao;

    private Date data_atualizacao;
}
