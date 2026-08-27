package com.jqdigitalsolutions.jqcommerce.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;
import java.util.Date;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;
import io.jsonwebtoken.security.Keys;
import javax.crypto.SecretKey;

@Service
public class JwtService {

    private static final String SECRET_KEY ="jqcommerce-jwt-secret-key-2026-jq-digital-solutions";
    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY.getBytes()
                )
                .compact();

    }
    public String extractUsername(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }

    public boolean isTokenValid(String token,
                                String username) {

        String tokenUsername = extractUsername(token);
        return tokenUsername.equals(username);
    }
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    // Ing_JQC: Genera refresh token
    public String generateRefreshToken(String username) {
        return Jwts.builder()
                .subject(username)
                .issuedAt( new Date())
                .expiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 604800000
                        )
                )
                .signWith(getSigningKey())
                .compact();
    }

}