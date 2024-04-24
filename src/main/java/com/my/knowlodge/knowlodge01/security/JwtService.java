package com.my.knowlodge.knowlodge01.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtService {
    private final String SECURITY_KEY = "BANANOUUUAINDANAOCOMEUBANANOUUUAINDANAOCOMEUJACOMEU00000JACOMEU11111";
    private final long EXPIRATION_DATE = 100000000L;
    private final long CURRENT_DATE = System.currentTimeMillis();

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(SECURITY_KEY));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(CURRENT_DATE))
                .expiration(new Date(CURRENT_DATE + EXPIRATION_DATE))
                .signWith(secretKey())
                .compact();
    }

    private Claims claims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return claims(token).getSubject();
    }

    public boolean isValid(String email, String token) {
        return getUsername(token).equals(email) && !isExpired(token);
    }

    private boolean isExpired(String token) {
        return claims(token).getExpiration().before(new Date());
    }
}
