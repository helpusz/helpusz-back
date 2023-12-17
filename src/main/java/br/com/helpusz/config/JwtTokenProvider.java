package br.com.helpusz.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

  @Value("${jwt.secret}")
  private String secret;

  private final long validityInMilliseconds = 3600000;

  public String createToken(String username) {
    Date now = new Date();
    Date validity = new Date(now.getTime() + validityInMilliseconds);

    return Jwts.builder()
      .setSubject(username)
      .setIssuedAt(now)
      .setExpiration(validity)
      .signWith(SignatureAlgorithm.HS256, secret)
      .compact();
    }

    public String getUsernameFromToken(String token) {
      return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
    }

    public boolean validateToken(String token) {
      try {
        Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        return true;
      } 
      catch (Exception e) {
        return false;
      }
    }
}
