package com.example.boiler.security.jwt;

import com.example.boiler.security.CustomUserDetails;
import io.jsonwebtoken.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;

import java.util.Date;

@Slf4j
@RequiredArgsConstructor
@Component
public class JwtProvider {
  @Value("${jwt.secret}")
  private String secret;
  @Value("${jwt.token-validity-in-seconds}")
  private long expirationInMs;

  private final UserDetailsService userDetailsService;

  public String createToken(Authentication authentication) {
    CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

    Date now = new Date();
    Date expiryDate = new Date(now.getTime() + this.expirationInMs);

    return Jwts.builder()
               .setSubject(userDetails.getUserId())
               .setIssuedAt(now)
               .setExpiration(expiryDate)
               .signWith(SignatureAlgorithm.HS512, this.secret)
               .compact();
  }

  public Authentication getAuthentication(String token) {
    UserDetails userDetails = userDetailsService.loadUserByUsername(this.getUserIdFromToken(token));
    return new UsernamePasswordAuthenticationToken(userDetails, "", userDetails.getAuthorities());
  }

  public String getUserIdFromToken(String token) {
    return Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody().getSubject();
  }

  public boolean validateToken(String token) {
    try {
      Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token);
      return true;
    } catch (SignatureException | MalformedJwtException e) {
      log.error("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.error("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.error("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.error("JWT 토큰이 잘못되었습니다.");
    }
    return false;
  }
}
