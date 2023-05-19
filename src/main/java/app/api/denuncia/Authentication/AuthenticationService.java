package app.api.denuncia.Authentication;

import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.api.denuncia.AES.AES256Service;
import app.api.denuncia.Configuration.JwtService;
import app.api.denuncia.Constants.Message;
import app.api.denuncia.Dto.Response;
import app.api.denuncia.Enums.ResponseType;
import app.api.denuncia.Models.DenuncianteModel;
import app.api.denuncia.Models.UtilizadorModel;
import app.api.denuncia.Repositories.DenuncianteRepository;
import app.api.denuncia.Repositories.UtilizadorRepository;
import app.api.denuncia.Utilities.GlobalFunctions;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UtilizadorRepository UserRepository;
  private final DenuncianteRepository DenunRepository;
  private final JwtService jwtService;
  private final AuthenticationManager authenticationManager;
  private final AES256Service aes256Service;

  private Message message = new Message();
  private List<String> msg = new ArrayList<>();
  private GlobalFunctions gf = new GlobalFunctions();

  public Response authenticate(AuthenticationRequest request) {

    gf.clearList(msg);

    try {

      String pass = aes256Service.decrypt(request.getPassword());

      if (pass != null) {

        Authentication authentication = authenticationManager
            .authenticate(new UsernamePasswordAuthenticationToken(request.getUsername(), pass));
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        msg.add(message.getMessage13("Login"));
        return sendToken(request.getCanal(), userDetails);

      } else {
        msg.add(message.getMessage14("decrypt"));
        return gf.getResponseError(msg);
      }

    } catch (BadCredentialsException | IllegalArgumentException | NoSuchElementException ex) {

      msg.add(message.getMessage12());
      return gf.getResponseError(msg);

    } catch (Exception e) {
      msg.add(message.getMessage04());
      return gf.getResponseError(msg);
    }
  }

  public Response sendToken(String canal, UserDetails userDetails) {

    LocalDateTime now = LocalDateTime.now();
    Integer userid = null;
    String username = null;

    if (userDetails instanceof UtilizadorModel) {

      UtilizadorModel user = (UtilizadorModel) userDetails;
      userid = user.getId();
      username = user.getUsername();

    } else if (userDetails instanceof DenuncianteModel) {

      DenuncianteModel user = (DenuncianteModel) userDetails;
      userid = user.getId();
      username = user.getUsername();
    }

    if (userid != null && username != null) {

      if (canal.equals("BackOffice")) {

        var jwtToken = jwtService.generateToken(userDetails);
        UserRepository.insertToken(jwtToken, now, userid, username).orElseThrow();
        return gf.getResponse(1, ResponseType.Sucesso, msg, jwtToken);

      } else {

        var jwtToken = jwtService.generateToken(userDetails);
        DenunRepository.insertToken(jwtToken, now, userid, username).orElseThrow();
        return gf.getResponse(1, ResponseType.Sucesso, msg, jwtToken);

      }
    } else {
      msg.add(message.getMessage14("envio de token"));
      return gf.getResponseError(msg);
    }
  }

  public Response logout(String canal) {

    try {

      if (userAutenticado() != null) {

        Integer result = null;

        if (canal.equals("BackOffice")) {
          result = UserRepository.logout(getUtiLogado().getId());
        } else {
          result = DenunRepository.logout(getDenunLogado().getId());
        }

        if (result != null) {

          msg.add(message.getMessage13("Logout"));
          return gf.getResponse(1, ResponseType.Sucesso, msg, null);

        } else {
          msg.add(message.getMessage14("logout"));
          return gf.getResponseError(msg);
        }
      } else {
        msg.add(message.getMessage17());
        return gf.getResponseError(msg);
      }
    } catch (Exception e) {
      msg.add(message.getMessage04());
      return gf.getResponseError(msg);
    }
  }

  public UserDetails userAutenticado() {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

    if (authentication != null) {
      return (UserDetails) authentication.getPrincipal();
    }
    return null;
  }

  public UtilizadorModel getUtiLogado() {

    UserDetails user = userAutenticado();
    if (user != null) {
      return (UtilizadorModel) user;
    }
    return null;
  }

  public DenuncianteModel getDenunLogado() {

    UserDetails user = userAutenticado();
    if (user != null) {
      return (DenuncianteModel) user;
    }
    return null;
  }
}
