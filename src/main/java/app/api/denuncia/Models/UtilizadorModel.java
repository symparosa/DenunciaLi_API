package app.api.denuncia.Models;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "dn_t_utilizador_backoffice")
public class UtilizadorModel implements UserDetails {

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

    @Schema(description = "O token de acesso do utilizador", hidden = true)
    private String token;

    @Schema(description = "A data do token do utilizador", hidden = true)
    private LocalDateTime token_iat;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(tipoUtilizador.getValor()));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
