package app.api.denuncia.Authentication;

import lombok.RequiredArgsConstructor;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import app.api.denuncia.Models.ResponseModel;
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

  @Operation(summary = "Login", description = "Login")
  @PostMapping("/login")
  public ResponseEntity<ResponseModel> authenticate(@RequestBody AuthenticationRequest request) {
    return ResponseEntity.ok(service.authenticate(request));
  }

  @SecurityRequirement(name = "Bearer Authentication")
  @Operation(summary = "Logout", description = "Logout")
  @PostMapping("/logout")
  public ResponseEntity<ResponseModel> logout() {
    return ResponseEntity.ok(service.logout());
  }
}
