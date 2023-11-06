package app.api.denuncia;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import app.api.denuncia.Authentication.AuthenticationRequest;
import app.api.denuncia.Dto.Response;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthenticationTest {

    @Autowired
    private TestRestTemplate restTemplate;
    
    @Test
    public void login(){

        AuthenticationRequest request = new AuthenticationRequest("AdminSistema@gmail.com", "yETSiJYQU0lU+vY2HUcvWg==", "BackOffice");

        HttpEntity<AuthenticationRequest> httpEntity = new HttpEntity<>(request);

        ResponseEntity<Response> response = this.restTemplate
            .exchange("/api/auth/login", HttpMethod.POST, httpEntity, Response.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        //assertEquals(response.getBody().getResponseCode(), 1);
    }

    @Test
    public void logout(){

        HttpHeaders headers = new HttpHeaders();
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJyb2xlcyI6W3siYXV0aG9yaXR5IjoiQURNSU4ifV0sInN1YiI6IkFkbWluU2lzdGVtYUBnbWFpbC5jb20ifQ.iLCYyz8FXJrIB4h7Wm7jaa7gBgFjxRjDWk1dxNY37fY";
        String canal = "BackOffice";
        headers.add("Authorization", "Bearer " + token);
        HttpEntity<String> httpEntity = new HttpEntity<>(canal, headers);

        ResponseEntity<Response> response = this.restTemplate
            .exchange("/api/auth/logout", HttpMethod.POST, httpEntity, Response.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        //assertEquals(response.getBody().getResponseCode(), 1);
    }
}
