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
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "dn_t_denunciante")
public class DenuncianteModel implements UserDetails {

    @Schema(description = "O identificador (ID) do denunciante")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Schema(description = "O username do denunciante")
    @Column(unique = true, nullable = false)
    private String username;

    @Schema(description = "O password do denunciante", hidden = true)
    private String password;

    @Schema(description = "O hash do denunciante", hidden = true)
    private String hash;

    @Schema(description = "O nome do denunciante")
    @Column(nullable = false)
    private String nome;

    @Schema(description = "O apelido do denunciante")
    private String apelido;

    @Schema(description = "O genero do denunciante")
    private String genero;

    @Schema(description = "O documento de identificação do denunciante")
    @Column(name = "doc_identificacao")
    private String docIdentificacao;

    @Schema(description = "A foto de perfil do denunciante")
    @Lob
    private String foto_perfil;

    @Schema(description = "A data de nascimento do denunciante")
    private Date data_nascimento;

    @Schema(description = "A referencia de morada do denunciante")
    private String referencia_morada;

    @Schema(description = "O codigo postal do denunciante")
    private String codigo_postal;

    @Schema(description = "A localização do denunciante")
    @ManyToOne()
    @JoinColumn(name = "localizacao_fk")
    private LocalizacaoModel localizacao;

    @Schema(description = "A localização no mapa do denunciante")
    private String localizacao_mapa;

    @Schema(description = "O estado do denunciante", hidden = true)
    private Integer estado;

    @Schema(description = "A data de criação do denunciante", hidden = true)
    private Date data_criacao;

    @Schema(description = "A data de atualização do denunciante", hidden = true)
    private Date data_atualizacao;

    @Schema(description = "O token de acesso do denunciante", hidden = true)
    private String token;

    @Schema(description = "A data do token do denunciante", hidden = true)
    private LocalDateTime token_iat;

    @Schema(description = "A validação da conta", hidden = true)
    @Column(name = "conta_confirmada")
    private Boolean contaConfirmada;

    @Schema(description = "Id do último utilizador a alterar os dados", hidden = true)
    private Integer last_user_change;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("DENUNCIANTE"));
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
