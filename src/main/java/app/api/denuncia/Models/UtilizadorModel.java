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
@Table(name = "dn_t_utilizador_backoffice")
public class UtilizadorModel implements Serializable {

    @Schema(description = "O identificador (ID) do utilizador")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O username do utilizador")
    @Column(unique = true, nullable = false)
    private String username;

    @Schema(description = "O password do utilizador", hidden = true)
    private String password;

    @Schema(description = "O hash do utilizador", hidden = true)
    private String hash;

    @Schema(description = "O nome do utilizador")
    @Column(nullable = false)
    private String nome;

    @Schema(description = "A foto de perfil do utilizador")
    @Lob
    private String fotoPerfil;

    @Schema(description = "O identificador (ID) da localização")
    @ManyToOne()
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "O identificador (ID) do tipo de utilizador")
    @ManyToOne
    @JoinColumn(name = "tipo_utilizador_fk")
    private DominioModel tipoUtilizador;

    @Schema(description = "O identificador (ID) da entidade")
    @ManyToOne
    @JoinColumn(name = "entidade_fk")
    private EntidadeModel entidade;

    @Schema(description = "A validação da conta", hidden = true)
    @Column(name = "conta_confirmada")
    private Boolean contaConfirmada;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;   

    @Schema(description = "O estado do utilizador", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do utilizador", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do utilizador", hidden = true)
    private Date data_atualizacao;
}
