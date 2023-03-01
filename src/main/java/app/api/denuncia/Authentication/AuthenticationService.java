package app.api.denuncia.Authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import app.api.denuncia.Configuration.JwtService;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.UtilizadorRepository;

@Getter
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UtilizadorRepository repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  public UtilizadorModel userLogado;

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()
        )
    );
    userLogado = repository.findByUsername(request.getUsername()).orElseThrow();
    var jwtToken = jwtService.generateToken(userLogado);
    final LocalDateTime now = LocalDateTime.now();
    repository.insertToken(jwtToken,now,userLogado.getUsername()).orElseThrow();
    return AuthenticationResponse.builder()
        .token(jwtToken)
        .build();
  }
}
