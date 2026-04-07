package com.young.erp_system.authservice.infrastructure.jwt;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.util.Date;

@Component
public class JwtProvider {

    private static final String TOKEN_PREFIX = "Bearer ";

    private final Key secretKey;
    private final long accessTokenExpiration;

    //yml에 설정한 값을 주입받아 서명 키(Key) 객체로 변환
    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.expiration_time}") long accessTokenExpiration) {
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessTokenExpiration = accessTokenExpiration;
    }

    // Access Token 발급
    public JwtToken createAccessToken(String email, String role) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + accessTokenExpiration);

        String accessToken = Jwts.builder()
                .setSubject(email)
                .claim("role", role) // 권한 정보 추가
                .setIssuedAt(now)
                .setExpiration(validity)
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
        return new JwtToken(accessToken, TOKEN_PREFIX, accessTokenExpiration / 1000);
    }
}
