package app.api.denuncia.Authentication;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import app.api.denuncia.Configuration.JwtService;
import app.api.denuncia.Constants.GlobalFunctions;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Constants.ResponseType;
import app.api.denuncia.Models.ResponseModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.UtilizadorRepository;

@Getter
@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UtilizadorRepository repository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private UtilizadorModel userLogado;
  private Message message = new Message();
  private List<String> msg = new ArrayList<>();
  private GlobalFunctions gf = new GlobalFunctions();

  public ResponseModel authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getUsername(),
            request.getPassword()));

    gf.clearList(msg);

    try {

      userLogado = repository.findByUsername(request.getUsername()).orElseThrow();
      var jwtToken = jwtService.generateToken(userLogado);
      final LocalDateTime now = LocalDateTime.now();
      repository.insertToken(jwtToken, now, userLogado.getUsername()).orElseThrow();

      msg.add(message.getMessage13("Login"));
      return gf.getResponse(1, ResponseType.Sucesso, msg, "token: " + jwtToken);

    } catch (Exception e) {
      msg.add(message.getMessage04());
      return gf.getResponseError(msg);
    }
  }

  public ResponseModel logout() {

    gf.clearList(msg);

    try {

      if (getUserLogado() != null) {

        Integer result = repository.logout(getUserLogado().getId());

        if (result != null) {

          msg.add(message.getMessage13("Logout"));
          return gf.getResponse(1, ResponseType.Sucesso, msg, null);

        } else {
          msg.add(message.getMessage14("logout"));
          return gf.getResponseError(msg);
        }
      } else {
        msg.add(message.getMessage12());
        return gf.getResponseError(msg);
      }
    } catch (Exception e) {
      msg.add(message.getMessage04());
      return gf.getResponseError(msg);
    }
  }

  public UtilizadorModel getUserLogado() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {

      UtilizadorModel user = repository.findByUsername(authentication.getName()).orElse(null);

      if (user != null) {
        return user;
      }
      return null;
    }
    return null;
  }
}
