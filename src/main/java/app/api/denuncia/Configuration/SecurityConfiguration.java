package app.api.denuncia.Configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
      http
              .csrf(csrf -> csrf
                      .disable())
              .authorizeHttpRequests()
              .requestMatchers(
                      "/api/auth/login", "/api/utilizadorBackoffice/recuperarSenha", "/api/utilizadorBackoffice/recuperarConta",
                      "/v3/**", "/swagger-ui/**", "/api/denunciante/adicionar", "/api/denunciante/recuperarSenha",
                      "/api/denunciante/recuperarConta", "/api/denuncia/adicionarDenuncia", "/api/aes256/**","/api/banner/listar",
                      "/api/entidade/listar","/api/informacaoLegal/getInfoByTipo","/api/noticia/listar","/api/localizacao/**","/api/dominio/getDominio",
                      "/api/denuncia/sistemaPolicia","/api/noticia/get_detalhes_by_id")
              .permitAll()
              .anyRequest()
              .authenticated()
              .and()
              .sessionManagement(management -> management
                      .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
              .authenticationProvider(authenticationProvider)
              .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
    return http.build();
  }
}
