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
    private final String SECRET_KEY = "BANANOUUINDANAOCOMEUBANANOUUAINDANAOCOMEU,JACOMEU?000000000,JACOMEU?1111111111";
    private final long EXPIRATION_DATE = 1000000000L;
    private final long EXACTLY_DATE = System.currentTimeMillis();

    private SecretKey secretKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64URL.decode(SECRET_KEY));
    }

    public String generateToken(String email) {
        return Jwts.builder()
                .subject(email)
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(EXACTLY_DATE + EXPIRATION_DATE))
                .signWith(secretKey())
                .compact();
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public String getUsername(String token) {
        return getClaims(token).getSubject();
    }

    public Boolean isValid(String email, String token) {
        return getUsername(token).equals(email) && !isExpired(token);
    }

    private Boolean isExpired(String token) {
        return getClaims(token).getExpiration().before(new Date(EXACTLY_DATE));
    }
}
