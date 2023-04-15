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
@Table(name = "dn_t_denunciante")
public class DenuncianteModel implements UserDetails {

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
