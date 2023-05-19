package app.api.denuncia.AES;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@Tag(name = "AES256")
@ApiResponse(responseCode = "200", description = "Success response.")
@RequestMapping(path = "/api/aes256", produces = MediaType.APPLICATION_JSON_VALUE)
public class AES256Controller {

    private AES256Service aes256Service;

    public AES256Controller(AES256Service aes256Service) {
        this.aes256Service = aes256Service;
    }

    @Operation(summary = "Encrypt String", description = "Encripta uma string")
    @PostMapping(path = "/encryptString")
    public ResponseEntity<String> encryptString(
            @RequestBody String Valor) {
        return ResponseEntity.ok(aes256Service.encrypt(Valor));
    }

    @Operation(summary = "Decrypt String", description = "Decripta uma string")
    @PostMapping(path = "/decryptString")
    public ResponseEntity<String> decryptString(
            @RequestBody String Valor) {
        return ResponseEntity.ok(aes256Service.decrypt(Valor));
    }
}
