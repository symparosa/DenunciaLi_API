package app.api.denuncia.Configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import app.api.denuncia.Repositories.UtilizadorRepository;

@Service
public class JwtService {

  @Value("${spring.secretkey}")
  private String SECRET_KEY;

  @Value("${spring.minexp}")
  private int expMin;

  private UtilizadorRepository repository;

  public JwtService(UtilizadorRepository repository) {
    this.repository = repository;
  }

  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }

  public String generateToken(UserDetails userDetails) {
    Map<String, Object> extraClaims = new HashMap<>();
    extraClaims.put("roles", userDetails.getAuthorities());
    return generateToken(extraClaims, userDetails);
  }

  public String generateToken(
      Map<String, Object> extraClaims,
      UserDetails userDetails) {
    return Jwts
        .builder()
        .setClaims(extraClaims)
        .setSubject(userDetails.getUsername())
        .signWith(getSignInKey(), SignatureAlgorithm.HS256)
        .compact();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    var user = repository.findByUsername(username).orElseThrow();
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(user.getToken_iat());
  }

  private boolean isTokenExpired(LocalDateTime token_iat) {
    if (token_iat != null) {
      final LocalDateTime now = LocalDateTime.now();
      return Math.abs(ChronoUnit.MINUTES.between(now, token_iat)) >= expMin;
    }
    return true;
  }

  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
    return Keys.hmacShaKeyFor(keyBytes);
  }
}
