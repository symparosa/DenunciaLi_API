package app.api.denuncia.Configuration;

import jakarta.annotation.Nonnull;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Repositories.UtilizadorRepository;


@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UtilizadorRepository utiRepository;
  private final DenuncianteRepository denuRepository;
  private final UserDetailsService userDetailsService;

  @Override
  protected void doFilterInternal(
      @Nonnull HttpServletRequest request,
      @Nonnull HttpServletResponse response,
      @Nonnull FilterChain filterChain
  ) throws ServletException, IOException {
    final String authHeader = request.getHeader("Authorization");
    final String jwt;
    final String username;
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      filterChain.doFilter(request, response);
      return;
    }
    jwt = authHeader.substring(7);
    username = jwtService.extractUsername(jwt);
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);
      if (jwtService.isTokenValid(jwt, userDetails)) {
        updateDateToken(userDetails);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );
        authToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );
        SecurityContextHolder.getContext().setAuthentication(authToken);
      }
    }
    filterChain.doFilter(request, response);
  }

  public void updateDateToken(UserDetails userDetails){

    final LocalDateTime now = LocalDateTime.now();
    
    if (userDetails instanceof UtilizadorModel) {
      UtilizadorModel user = (UtilizadorModel) userDetails;
      utiRepository.updateDateToken(now, user.getId(), user.getUsername()).orElseThrow();
    } else if (userDetails instanceof DenuncianteModel) {
      DenuncianteModel user = (DenuncianteModel) userDetails;
      denuRepository.updateDateToken(now, user.getId(), user.getUsername()).orElseThrow();
    }
  }
}
