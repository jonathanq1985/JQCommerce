package com.jqdigitalsolutions.jqcommerce.auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY =
            "jqcommerce-jwt-secret-key-2026-jq-digital-solutions";

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
}