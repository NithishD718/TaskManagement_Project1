package com.userflow.user_service.Security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Base64;
import java.util.Collection;
import java.util.Date;
import java.util.function.Function;

@Component
public class JwtUtil {
    @Value("${jwt.secret}")
    private String clientSecret;
    @Value("${jwt.expiration}")
    private long expirationTime;
    private byte[] secretKey;

    @PostConstruct
    protected void init() {
        secretKey = Base64.getEncoder().encode(clientSecret.getBytes());
    }
    public String generateToken(String username , String role)
    {
        Claims claims = Jwts.claims().setSubject(username);
        claims.put("roles", role);
      return Jwts.builder().setClaims(claims)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + expirationTime))
              .signWith(SignatureAlgorithm.HS256,secretKey).compact();
    }

    public Boolean validateToken(String token , String username)
    {
        final String extractedUserName = extractUserName(token);
         return extractedUserName.equals(username) && !isTokenExpired(token);
    }

    public String extractUserName(String token)
    {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token)
    {
        return extractClaim(token,Claims::getExpiration);
    }

    public  <T> T extractClaim(String token , Function<Claims,T> claimsResolver)
    {
      final Claims claims = extractAllClaims(token);
       return claimsResolver.apply(claims);
    }

    public Claims extractAllClaims(String token)
    {
       return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
    }

    public Boolean isTokenExpired(String token)
    {
        return extractExpiration(token).before(new Date());
    }
}
