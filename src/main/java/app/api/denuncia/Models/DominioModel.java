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

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_dominio")
public class DominioModel implements Serializable {

    @Schema(description = "O identificador (ID) do domínio")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O domínio")
    @Column(nullable = false)
    private String dominio;

    @Schema(description = "O valor do domínio")
    @Column(nullable = false)
    private String valor;

    @Schema(description = "O descrição do domínio")
    @Lob
    private String descricao;

    @Schema(description = "O estado do domínio", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do domínio", hidden = true)
    private LocalDateTime data_criacao;

    @Schema(description = "A data de atualização do domínio", hidden = true)
    private LocalDateTime data_atualizacao;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;
}
