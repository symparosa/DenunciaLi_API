package app.api.denuncia.Authentication;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Dto.Response;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "Autenticação")
@ApiResponse(responseCode = "200", description = "Success response.")
@RequestMapping(path = "/api/auth", produces = MediaType.APPLICATION_JSON_VALUE)
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService service;

  @Operation(summary = "Login", description = "Realiza a autenticação do utilizador na plataforma.")
  @PostMapping("/login")
  public ResponseEntity<Response> authenticate(
      @RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Logout", description = "Encerra a sessão do utilizador na plataforma.")
  @PostMapping("/logout")
  public ResponseEntity<Response> logout(
      @RequestParam(required = true) String canal) {
    return ResponseEntity.ok(service.logout(canal));
  }
}
