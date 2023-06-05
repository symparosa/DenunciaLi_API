package app.api.denuncia.Configuration;

import lombok.RequiredArgsConstructor;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import app.api.denuncia.Constants.Status;
import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Repositories.UtilizadorRepository;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {

  @Autowired
  private UtilizadorRepository utiRepository;

  @Autowired
  private DenuncianteRepository denuRepository;

  private Status status = new Status();

  @Bean
  public UserDetailsService userDetailsService() {
    UserDetailsService userDetailsService = new UserDetailsService() {

      @Override
      public UserDetails loadUserByUsername(String username) {

        UtilizadorModel user1 = utiRepository.findByUsernameAndEstado(username, status.getAtivo()).orElse(null);

        if (user1 != null) {
          return user1;
        } else {
          DenuncianteModel user2 = denuRepository.findByUsernameAndEstado(username, status.getAtivo()).orElseThrow();
          return user2;
        }
      }
    };
    return userDetailsService;
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setUserDetailsService(userDetailsService());
    authProvider.setPasswordEncoder(passwordEncoder());
    return authProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
    return config.getAuthenticationManager();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}
