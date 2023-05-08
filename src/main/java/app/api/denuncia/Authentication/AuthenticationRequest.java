package app.api.denuncia.Authentication;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequest {

  @Schema(description = "O username do utilizador", example = "AdminSistema@gmail.com", required = true)
  private String username;

  @Schema(description = "O password do utilizador", example = "yETSiJYQU0lU+vY2HUcvWg==", required = true)
  private String password;

  @Schema(description = "O canal de utilização do utilizador", example = "BackOffice", required = true)
  private String canal;
}
