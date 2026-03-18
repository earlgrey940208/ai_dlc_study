package com.tableorder.security;

import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private final SecretKey key;
    private final long expiration;

    public JwtTokenProvider(@Value("${jwt.secret}") String secret,
                            @Value("${jwt.expiration}") long expiration) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.expiration = expiration;
    }

    public String generateAdminToken(Long adminId) {
        return buildToken(adminId, null, "ADMIN");
    }

    public String generateTableToken(Long tableId) {
        return buildToken(null, tableId, "TABLE");
    }

    private String buildToken(Long adminId, Long tableId, String role) {
        Date now = new Date();
        var builder = Jwts.builder()
                .subject(role)
                .claim("role", role)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expiration))
                .signWith(key);
        if (adminId != null) builder.claim("adminId", adminId);
        if (tableId != null) builder.claim("tableId", tableId);
        return builder.compact();
    }

    public Claims parseToken(String token) {
        return Jwts.parser().verifyWith(key).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }

    public String getRole(String token) {
        return parseToken(token).get("role", String.class);
    }

    public Long getAdminId(String token) {
        return parseToken(token).get("adminId", Long.class);
    }

    public Long getTableId(String token) {
        return parseToken(token).get("tableId", Long.class);
    }
}
